package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.OrderMaster;

/**
 * @author SiletFlower
 * @date 2021/5/20 03:33:30
 * @description
 */
public class OrderMasterExt extends OrderMaster {
    /**
     * 订单交易商家名
     */
    private String ShopName;

    /**
     * 查询的订单可能包含的商品名
     */
    private String goodSName;

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getGoodSName() {
        return goodSName;
    }

    public void setGoodSName(String goodSName) {
        this.goodSName = goodSName;
    }
}
