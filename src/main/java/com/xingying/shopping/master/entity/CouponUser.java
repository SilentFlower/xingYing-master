package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧商城用户优惠券表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@TableName(value = "COUPON_USER",schema = "XINGYING_SHOP")
public class CouponUser implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 卡券ID
     */
    @TableField("COUPON_ID")
    private String couponId;

    /**
     * 获取时间
     */
    @TableField("GET_DATE")
    private LocalDateTime getDate;

    /**
     * 使用状态
     */
    @TableField("STATUS")
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public LocalDateTime getGetDate() {
            return getDate;
    }

    public void setGetDate(LocalDateTime getDate) {
        this.getDate = getDate;
    }

    @Override
    public String toString() {
        return "CouponUser{" +
        "userId=" + userId +
        ", couponId=" + couponId +
        ", getDate=" + getDate +
        "}";
    }
}
