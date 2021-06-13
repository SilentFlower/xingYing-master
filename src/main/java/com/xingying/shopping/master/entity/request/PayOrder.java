package com.xingying.shopping.master.entity.request;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/6/13 01:58:08
 * @description
 */
public class PayOrder {
    /**
     * 需要支付的主订单
     */
    private String orderId;

    /**
     * 选择支付方式（1为钱包，2为其他）
     */
    private Integer payType;

    /**
     * 退回金额
     */
    private BigDecimal backAmount;

    public BigDecimal getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(BigDecimal backAmount) {
        this.backAmount = backAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
