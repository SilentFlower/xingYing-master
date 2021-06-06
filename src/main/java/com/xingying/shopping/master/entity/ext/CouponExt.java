package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.Coupon;

import java.time.LocalDateTime;

/**
 * @author SiletFlower
 * @date 2021/5/30 12:23:01
 * @description
 */
public class CouponExt extends Coupon {
    /**
     * 已使用的张数
     */
    private Integer haveUseCount;

    /**
     * 过期标志（查询用）
     */
    private Integer expiredFlag;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 查询开始时间
     */
    private LocalDateTime startTime;

    /**
     * 查询开始时间
     *
     */
    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getHaveUseCount() {
        return haveUseCount;
    }

    public void setHaveUseCount(Integer haveUseCount) {
        this.haveUseCount = haveUseCount;
    }

    public Integer getExpiredFlag() {
        return expiredFlag;
    }

    public void setExpiredFlag(Integer expiredFlag) {
        this.expiredFlag = expiredFlag;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
