package com.xingying.shopping.master.entity.request;
import com.xingying.shopping.master.entity.Goods;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/9 18:19:28
 * 优惠券请求
 * @description
 */
public class CouponRes {

    private List<Goods> goods;

    /**
     * 用户ID
     *
     */
    private String userId;

    /**
     * 启用标志
     */
    private Integer couponFlag;


    /**
     * 不可用查询
     *
     */
    private Integer unavailable;

    public Integer getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Integer unavailable) {
        this.unavailable = unavailable;
    }


    public Integer getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(Integer couponFlag) {
        this.couponFlag = couponFlag;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

}
