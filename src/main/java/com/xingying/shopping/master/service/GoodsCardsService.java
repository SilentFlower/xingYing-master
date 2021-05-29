package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.GoodsCards;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  GoodsCardsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-28
 */
public interface GoodsCardsService extends IService<GoodsCards> {

    PageInfo<GoodsCards> getListByPage(PageQueryEntity<GoodsCards> params);

    /**
     * 删除
     * @param goodsCard
     * @return
     */
    boolean delGoodsCard(GoodsCards goodsCard);

    /**
     * 更新库存
     * @param goodsCards
     */
    void updateGoods(GoodsCards goodsCards);
}
