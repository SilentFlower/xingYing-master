package com.xingying.shopping.master.entity.response;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/5/20 10:12:29
 * @description
 */
public class OrderStatistics {
    /**
     * 今日收入
     */
    private BigDecimal todaySEarnings;

    /**
     * 今日支出
     */
    private BigDecimal spendToday;

    /**
     * 今天订单
     */
    private Integer orderToday;

    /**
     * 总成交订单
     */
    private Integer totalTradedOrders;

    public BigDecimal getTodaySEarnings() {
        return todaySEarnings;
    }

    public void setTodaySEarnings(BigDecimal todaySEarnings) {
        this.todaySEarnings = todaySEarnings;
    }

    public BigDecimal getSpendToday() {
        return spendToday;
    }

    public void setSpendToday(BigDecimal spendToday) {
        this.spendToday = spendToday;
    }

    public Integer getOrderToday() {
        return orderToday;
    }

    public void setOrderToday(Integer orderToday) {
        this.orderToday = orderToday;
    }

    public Integer getTotalTradedOrders() {
        return totalTradedOrders;
    }

    public void setTotalTradedOrders(Integer totalTradedOrders) {
        this.totalTradedOrders = totalTradedOrders;
    }
}
