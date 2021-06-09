package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.Goods;

/**
 * @author SiletFlower
 * @date 2021/5/27 12:04:18
 * @description
 */
public class GoodsExt extends Goods {
    /**
     * 商品类别名
     */
    private String typeName;

    /**
     * 热门商品添加时间
     */
    private String hotAddTime;

    /**
     * 商家名
     */
    private String shopName;

    /**
     * 传参 用于区分是否查找未添加热门商品的商品（1启用）
     */
    private Integer hotFlag;

    /**
     * 最小价格
     */
    private String lowPrice;

    /**
     * 最大价格
     */
    private String highPrice;

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(Integer hotFlag) {
        this.hotFlag = hotFlag;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHotAddTime() {
        return hotAddTime;
    }

    public void setHotAddTime(String hotAddTime) {
        this.hotAddTime = hotAddTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
