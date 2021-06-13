package com.xingying.shopping.master.entity.response;

import com.xingying.shopping.master.entity.Coupon;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/12 15:40:54
 * @description
 */
public class DetailsOrder {
    private List<OrderAndCoupon> orderAndCoupons;

    /**
     * 店铺优惠券
     */
    private List<Coupon> coupon;

    public List<OrderAndCoupon> getOrderAndCoupons() {
        return orderAndCoupons;
    }

    public void setOrderAndCoupons(List<OrderAndCoupon> orderAndCoupons) {
        this.orderAndCoupons = orderAndCoupons;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
    }
}
