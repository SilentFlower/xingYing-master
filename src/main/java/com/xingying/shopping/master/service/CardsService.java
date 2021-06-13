package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Cards;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.entity.OrderDetail;
import com.xingying.shopping.master.entity.ext.GoodsAndOrderDetail;

/**
 * <p>
 *  CardsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
public interface CardsService extends IService<Cards> {

    PageInfo<Cards> getListByPage(PageQueryEntity<Cards> params);

    /**
     * 更新库存
     * @param cards
     */
    void updateGoods(Cards cards);

    /**
     * 卡密发货
     */
    boolean cardSend(GoodsAndOrderDetail goodsAndOrderDetail);
}
