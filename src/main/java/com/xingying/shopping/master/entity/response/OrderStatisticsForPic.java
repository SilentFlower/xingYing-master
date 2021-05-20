package com.xingying.shopping.master.entity.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author SiletFlower
 * @date 2021/5/20 18:58:37
 * 用于返回x日内的统计数据
 * @description
 */
public class OrderStatisticsForPic {
    /**
     * 时间
     */
    private LocalDateTime orderTime;

    /**
     * 提交的订单
     */
    private Integer submitOrders;

    /**
     * 成功的订单
     */
    private Integer successfulOrder;

    /**
     * 支出
     */
    private BigDecimal outcome;

    /**
     * 收入
     */
    private BigDecimal income;

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getSubmitOrders() {
        return submitOrders;
    }

    public void setSubmitOrders(Integer submitOrders) {
        this.submitOrders = submitOrders;
    }

    public Integer getSuccessfulOrder() {
        return successfulOrder;
    }

    public void setSuccessfulOrder(Integer successfulOrder) {
        this.successfulOrder = successfulOrder;
    }

    public BigDecimal getOutcome() {
        return outcome;
    }

    public void setOutcome(BigDecimal outcome) {
        this.outcome = outcome;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
