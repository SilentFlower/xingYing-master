package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.dao.WalletMapper;
import com.xingying.shopping.master.dao.WithdrawMapper;
import com.xingying.shopping.master.entity.Wallet;
import com.xingying.shopping.master.entity.WalletFlow;
import com.xingying.shopping.master.dao.WalletFlowMapper;
import com.xingying.shopping.master.entity.Withdraw;
import com.xingying.shopping.master.service.WalletFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Service
public class WalletFlowServiceImpl extends ServiceImpl<WalletFlowMapper, WalletFlow> implements WalletFlowService {

    @Autowired
    private WalletFlowMapper walletFlowMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private WithdrawMapper withdrawMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    @Override
    public PageInfo<WalletFlow> getListByPage(PageQueryEntity<WalletFlow> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<WalletFlow> list = walletFlowMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 充值
     * @param walletFlow
     * @return
     */
    @Override
    public String recharge(WalletFlow walletFlow) {
        String id = String.valueOf(snowFakeIdGenerator.nextId());
        walletFlow.setWalletFlowId(id);
        walletFlow.setWalletFlowType(1);
        walletFlow.setUserId(String.valueOf(UserContext.getCurrentUser().getUserId()));
        walletFlow.setWalletFlowDate(LocalDateTime.now());
        walletFlow.setWalletFlowStatus(0);
        return id;
    }

    /**
     * 提现
     * @param walletFlow
     * @return
     */
    @Override
    public String withdraw(WalletFlow walletFlow) {
        String userId = UserContext.getCurrentUser().getUserId();
        //检查钱包剩余可用余额,并与提现金额对比
        Wallet wallet = walletMapper.selectById(userId);
        BigDecimal balance = wallet.getBalance();
        Assert.isTrue(balance.compareTo(walletFlow.getWalletFlowFee()) > 1, "钱包余额不足");
        String id = String.valueOf(snowFakeIdGenerator.nextId());
        walletFlow.setWalletFlowId(id);
        walletFlow.setWalletFlowType(2);
        walletFlow.setUserId(userId);
        walletFlow.setWalletFlowDate(LocalDateTime.now());
        walletFlow.setWalletFlowStatus(0);
        return id;
    }

    /**
     * 下单
     * @param walletFlow
     * @return
     */
    @Override
    public String placeAnOrder(WalletFlow walletFlow) {
        String id = String.valueOf(snowFakeIdGenerator.nextId());
        walletFlow.setWalletFlowId(id);
        walletFlow.setWalletFlowType(3);
        walletFlow.setUserId(String.valueOf(UserContext.getCurrentUser().getUserId()));
        walletFlow.setWalletFlowDate(LocalDateTime.now());
        walletFlow.setWalletFlowStatus(0);
        return id;
    }

    /**
     * 提现预检查
     * @return
     */
    @Override
    public String withdrawPre() {
        String userId = UserContext.getCurrentUser().getUserId();
        //检查是否补齐了收款方式
        Integer count = withdrawMapper.selectCount(new QueryWrapper<Withdraw>()
                .eq("USER_ID", userId));
        if (count == 0) {
            return "未补齐收款方式";
        }
        return "正常";
    }
}
