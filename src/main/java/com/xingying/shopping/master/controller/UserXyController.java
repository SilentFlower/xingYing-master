package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.config.security.handler.AuthenticationSuccessHandlerImpl;
import com.xingying.shopping.master.entity.UserAuths;
import com.xingying.shopping.master.entity.UserEntity;
import com.xingying.shopping.master.service.UserAuthsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.netty.handler.codec.base64.Base64Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import com.xingying.shopping.master.service.UserXyService;
import com.xingying.shopping.master.entity.UserXy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 星荧虚拟商品交易系统的用户表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-03-22
 */
@RestController
@RequestMapping("${xingYing.name}/user")
        public class UserXyController {

    @Autowired
    private UserXyService userXyService;
    @Autowired
    private UserAuthsService userAuthsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;


    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<UserXy>> getListByPage(@RequestBody PageQueryEntity<UserXy> params) {
        PageInfo<UserXy> page = userXyService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧虚拟商品交易系统的用户表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getUserByParam")
    public QueryResultBean<UserXy> getUserXy(@RequestParam String key) {
        UserXy userXy = userXyService.getById(key);
        return new QueryResultBean<>(userXy);
    }

    /**
     * 新增 星荧虚拟商品交易系统的用户表
     * @param user UserXy 对象
     * @return
     */
    @PostMapping("/addUser")
    public OperationResultBean<String> addUserXy(@RequestBody UserXy user) {
        boolean b = userXyService.addUser(user);
        Assert.isTrue(b,"注册失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delUsers")
    public OperationResultBean<String> delUserXys(@RequestParam List<String> keys) {
        boolean b = userXyService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 通过google第三登陆
     */
    @PostMapping("/loginWithGoogle")
    public OperationResultBean<String> delUserXys(@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response) {
        UserAuths one = userAuthsService.getOne(new QueryWrapper<UserAuths>()
                .eq("IDENTIFIER", map.get("email"))
                .eq("IDENTITY_TYPE", "google"));
        //如果授权表无数据那么创建新用户
        Long uid = null;
        if (one == null) {
            uid = userXyService.addUserByGoogle(map);
        }else{
            uid = one.getUserId();
        }
        UserEntity userInfo = jwtTokenUtil.getUserbyId(String.valueOf(uid));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        try {
            authenticationSuccessHandler.onAuthenticationSuccess(request,response,authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改密码
     * {
     *     oldPwd://旧密码
     *     newPwd://新密码
     * }
     *
     * @param map key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/editPwd")
    public OperationResultBean<String> editPwd(@RequestBody Map<String,Object> map) {
        String msg =  userXyService.editPwd(map);
        return new OperationResultBean<>(msg);
    }

    /**
     *{
     *     account://账户名
     *     pwd://密码
     * }
     * 第三方登陆完善用户名和密码
     * @param map
     * @return
     */
    @PostMapping("/completePwd")
    public OperationResultBean<String> completePwd(@RequestBody Map<String,Object> map) {
        String msg = userXyService.completePwd(map);
        return new OperationResultBean<>(msg);
    }
}
