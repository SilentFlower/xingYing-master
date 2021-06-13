package com.xingying.shopping.master.entity.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xingying.shopping.master.entity.Coupon;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/12 15:35:11
 * @description
 */
public class OrderAndCoupon {
    /**
     * 订单详细表ID
     */
    private String orderDetailId;

    /**
     * 订单主笔ID
     */
    private String orderId;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总价
     */
    private BigDecimal sum;

    /**
     * 商品规格
     */
    private String goodsSpc;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPic;

    /**
     * 顾客ID
     */
    private String userId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商店ID
     */
    private String shopId;

    /**
     * 状态(0不启用 1启用 2已经发货)
     */
    private Integer status;

    /**
     * 发送的信息 (发送用)
     */
    private String message;

    /**
     * 商品使用的优惠券
     */
    private List<Coupon> coupon;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getGoodsSpc() {
        return goodsSpc;
    }

    public void setGoodsSpc(String goodsSpc) {
        this.goodsSpc = goodsSpc;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

}
