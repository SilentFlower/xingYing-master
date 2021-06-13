package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.GoodsDetails;

/**
 * @author SiletFlower
 * @date 2021/5/28 05:04:16
 * @description
 */
public class GoodsDetailsExt extends GoodsDetails {
    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 传值的卡密
     * @return
     */
    private String cards;


    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
