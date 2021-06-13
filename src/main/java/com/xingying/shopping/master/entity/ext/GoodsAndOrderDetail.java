package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.entity.OrderDetail;

/**
 * @author SiletFlower
 * @date 2021/6/13 02:32:57
 * @description
 */
public class GoodsAndOrderDetail{
    private OrderDetail orderDetail;
    private GoodsDetails goodsDetails;
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public GoodsDetails getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(GoodsDetails goodsDetails) {
        this.goodsDetails = goodsDetails;
    }
}
