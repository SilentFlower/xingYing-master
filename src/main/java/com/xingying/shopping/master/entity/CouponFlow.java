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
 * 星荧优惠券使用记录表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@TableName(value = "COUPON_FLOW",schema = "XINGYING_SHOP")
public class CouponFlow implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 订单详细表ID
     */
    @TableId(value = "ORDER_DETAIL_ID", type = IdType.INPUT)
    private String orderDetailId;

    /**
     * 优惠券ID
     */
    @TableField("COUPON_ID")
    private String couponId;

    /**
     * 使用时间
     */
    @TableField("USE_DATE")
    private LocalDateTime useDate;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public LocalDateTime getUseDate() {
            return useDate;
    }

    public void setUseDate(LocalDateTime useDate) {
        this.useDate = useDate;
    }

    @Override
    public String toString() {
        return "CouponFlow{" +
        "orderDetailId=" + orderDetailId +
        ", couponId=" + couponId +
        ", useDate=" + useDate +
        "}";
    }
}
