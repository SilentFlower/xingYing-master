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
 * 星荧商城的优惠券表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@TableName(value = "COUPON",schema = "XINGYING_SHOP")
public class Coupon implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 优惠券ID
     */
    @TableId(value = "COUPON_ID", type = IdType.INPUT)
    private String couponId;

    /**
     * 优惠券类型
     */
    @TableField("COUPON_TYPE")
    private String couponType;

    /**
     * 商品优惠券对应的商品ID
     */
    @TableField("GOODS_ID")
    private String goodsId;

    /**
     * 使用类型
     */
    @TableField("COUPON_USE_TYPE")
    private String couponUseType;

    /**
     * 使用价值
     */
    @TableField("COUPON_VALUE")
    private BigDecimal couponValue;

    /**
     * 使用限制金额
     */
    @TableField("COUPON_LIMIT")
    private BigDecimal couponLimit;

    /**
     * 优惠券创建时间
     */
    @TableField("COUPON_DATE_CREATE")
    private LocalDateTime couponDateCreate;

    /**
     * 优惠券过期时间
     */
    @TableField("COUPON_DATE_END")
    private LocalDateTime couponDateEnd;

    /**
     * 启用标志(1表示启用)
     */
    @TableField("COUPON_FLAG")
    private Integer couponFlag;

    /**
     * 优惠券张数
     */
    @TableField("COUPON_NUM")
    private Long couponNum;

    /**
     * 对应的店铺ID
     */
    @TableField("SHOP_ID")
    private String shopId;

    /**
     * 优惠券名
     */
    @TableField("COUPON_NAME")
    private String couponName;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCouponType() {
            return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }



    public String getCouponUseType() {
            return couponUseType;
    }

    public void setCouponUseType(String couponUseType) {
        this.couponUseType = couponUseType;
    }

    public BigDecimal getCouponValue() {
            return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getCouponLimit() {
            return couponLimit;
    }

    public void setCouponLimit(BigDecimal couponLimit) {
        this.couponLimit = couponLimit;
    }

    public LocalDateTime getCouponDateCreate() {
            return couponDateCreate;
    }

    public void setCouponDateCreate(LocalDateTime couponDateCreate) {
        this.couponDateCreate = couponDateCreate;
    }

    public LocalDateTime getCouponDateEnd() {
            return couponDateEnd;
    }

    public void setCouponDateEnd(LocalDateTime couponDateEnd) {
        this.couponDateEnd = couponDateEnd;
    }

    public Integer getCouponFlag() {
            return couponFlag;
    }

    public void setCouponFlag(Integer couponFlag) {
        this.couponFlag = couponFlag;
    }

    public Long getCouponNum() {
            return couponNum;
    }

    public void setCouponNum(Long couponNum) {
        this.couponNum = couponNum;
    }



    @Override
    public String toString() {
        return "Coupon{" +
        "couponId=" + couponId +
        ", couponType=" + couponType +
        ", goodsId=" + goodsId +
        ", couponUseType=" + couponUseType +
        ", couponValue=" + couponValue +
        ", couponLimit=" + couponLimit +
        ", couponDateCreate=" + couponDateCreate +
        ", couponDateEnd=" + couponDateEnd +
        ", couponFlag=" + couponFlag +
        ", couponNum=" + couponNum +
        ", shopId=" + shopId +
        "}";
    }
}
