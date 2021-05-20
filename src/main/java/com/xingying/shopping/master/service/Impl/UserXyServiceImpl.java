package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.dao.UserAuthsMapper;
import com.xingying.shopping.master.dao.UserToRoleMapper;
import com.xingying.shopping.master.dao.WalletMapper;
import com.xingying.shopping.master.entity.UserAuths;
import com.xingying.shopping.master.entity.UserToRole;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.UserXy;
import com.xingying.shopping.master.dao.UserXyMapper;
import com.xingying.shopping.master.entity.Wallet;
import com.xingying.shopping.master.service.UserXyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-03-22
 */
@Service
public class UserXyServiceImpl extends ServiceImpl<UserXyMapper, UserXy> implements UserXyService {

    @Autowired
    private UserXyMapper userXyMapper;
    @Autowired
    private UserToRoleMapper userToRoleMapper;
    @Autowired
    private UserAuthsMapper userAuthsMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;
    @Autowired
    private WalletService walletService;
    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PageInfo<UserXy> getListByPage(PageQueryEntity<UserXy> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<UserXy> list = userXyMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserXy user) {
        //先获取用户的账号并查询数据库中是否存在
        String account = user.getAccount();
        Integer count = userXyMapper.selectCount(new QueryWrapper<UserXy>().eq("ACCOUNT", account));
        Assert.isTrue(count == 0, String.format("账号[%s]已经被注册", account));
        //生成Uid
        Long uid = snowFakeIdGenerator.nextId();
        user.setUserId(String.valueOf(uid));
        user.setDataCreateTime(LocalDateTime.now());
        user.setEnabled(1);
        user.setDeleted(0);
        user.setPasswords(bCryptPasswordEncoder.encode(user.getPasswords()));
        int insert = userXyMapper.insert(user);
        if(insert > 0){
            //设置用户为普通用户
            UserToRole userToRole = new UserToRole();
            //默认普通用户为0
            userToRole.setRoleId((long) 0);
            userToRole.setUserId(uid);
            int insert1 = userToRoleMapper.insert(userToRole);
            Assert.isTrue(insert1 > 0, "新建用户角色失败");
            //创建对应钱包
            walletService.createWallet(String.valueOf(uid));
            return true;
        }else{
            throw new RuntimeException("新建用户失败");
        }
    }

    /**
     * google登陆生成用户信息并返回UID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addUserByGoogle(Map<String,String> map) {
        UserXy userXy = new UserXy();
        Long uid = snowFakeIdGenerator.nextId();
        userXy.setUserId(String.valueOf(uid));
        userXy.setDataCreateTime(LocalDateTime.now());
        userXy.setEnabled(1);
        userXy.setDeleted(0);
        userXy.setNickName(map.get("name"));
        int insert = userXyMapper.insert(userXy);
        if (insert > 0) {
            //设置用户未普通用户
            UserToRole userToRole = new UserToRole();
            //默认普通用户为0
            userToRole.setRoleId((long) 0);
            userToRole.setUserId(uid);
            int insert1 = userToRoleMapper.insert(userToRole);
            Assert.isTrue(insert1 > 0, "新建用户角色失败");
            //生成用户钱包表
            walletService.createWallet(String.valueOf(uid));
        }
        UserAuths auths = new UserAuths();
        auths.setUserId(uid);
        auths.setIdentityType("google");
        auths.setIdentifier(map.get("email"));
        userAuthsMapper.insert(auths);
        return uid;
    }

    @Override
    public String editPwd(Map<String, Object> map) {
        String oldPwd = (String) map.get("oldPwd");
        String newPwd = (String) map.get("newPwd");
        UserXy info = userXyMapper.selectById(UserContext.getCurrentUser().getUserId());
        //首先判断旧密码与新密码是否一致
        if (bCryptPasswordEncoder.matches(oldPwd, info.getPasswords())) {
            String newPwdEncode = bCryptPasswordEncoder.encode(newPwd);
            UserXy update = new UserXy();
            update.setUserId(UserContext.getCurrentUser().getUserId());
            update.setPasswords(newPwdEncode);
            //更新密码操作
            int i = userXyMapper.updateById(update);
            Assert.isTrue(i > 0, "密码修改失败");
            //踢出当前所有用户
            jwtTokenUtil.redisDelById(String.valueOf(UserContext.getCurrentUser().getUserId()));
        }else{
            Assert.isTrue(false,"旧密码错误");
        }
        return "密码修改成功";
    }

    @Override
    public String completePwd(Map<String, Object> map) {
        String account = (String) map.get("account");
        String pwd = (String) map.get("pwd");
        //判断账户名是否存在
        Integer count = userXyMapper.selectCount(new QueryWrapper<UserXy>()
                .eq("ACCOUNT", account));
        Assert.isTrue(count == 0,"账户名已经存在,请修改");
        String pwdEncode = bCryptPasswordEncoder.encode(pwd);
        UserXy update = new UserXy();
        update.setUserId(UserContext.getCurrentUser().getUserId());
        update.setPasswords(pwdEncode);
        update.setAccount(account);
        //更新操作
        int i = userXyMapper.updateById(update);
        Assert.isTrue(i > 0, "完善失败");
        //踢出当前所有用户
        jwtTokenUtil.redisDelById(String.valueOf(UserContext.getCurrentUser().getUserId()));
        return "密码修改成功";
    }
}
