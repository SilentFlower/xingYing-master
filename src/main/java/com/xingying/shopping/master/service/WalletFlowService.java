package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.WalletFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  WalletFlowService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
public interface WalletFlowService extends IService<WalletFlow> {

    PageInfo<WalletFlow> getListByPage(PageQueryEntity<WalletFlow> params);

    /**
     * 充值
     * @param walletFlow
     * @return
     */
    String recharge(WalletFlow walletFlow);

    /**
     * 提现
     * @param walletFlow
     * @return
     */
    String withdraw(WalletFlow walletFlow);

    /**
     * 下单
     * @param walletFlow
     * @return
     */
    String placeAnOrder(WalletFlow walletFlow);

    /**
     * 提现预检查
     * @return
     */
    String withdrawPre();
}
