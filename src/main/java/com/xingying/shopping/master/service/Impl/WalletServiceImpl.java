package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Wallet;
import com.xingying.shopping.master.dao.WalletMapper;
import com.xingying.shopping.master.service.WalletService;
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
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Override
    public PageInfo<Wallet> getListByPage(PageQueryEntity<Wallet> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Wallet> list = walletMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 用户创建时生成钱包表
     */
    @Override
    public boolean createWallet(String uid) {
        Wallet wallet = new Wallet();
        wallet.setUserId(String.valueOf(uid));
        wallet.setBalance(BigDecimal.valueOf(0.00));
        wallet.setWalletIncome(BigDecimal.valueOf(0.00));
        wallet.setWalletOutcome(BigDecimal.valueOf(0.00));
        wallet.setBalanceDisable(BigDecimal.valueOf(0.00));
        wallet.setUpdateDate(LocalDateTime.now());
        int insert = walletMapper.insert(wallet);
        Assert.isTrue(insert > 0, "钱包表生成失败");
        return true;
    }
}
