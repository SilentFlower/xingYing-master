package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Wallet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  WalletService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
public interface WalletService extends IService<Wallet> {

    PageInfo<Wallet> getListByPage(PageQueryEntity<Wallet> params);

    /**
     * 用户创建时生成钱包表
     * @param uid
     * @return
     */
    public boolean createWallet(String uid);
}
