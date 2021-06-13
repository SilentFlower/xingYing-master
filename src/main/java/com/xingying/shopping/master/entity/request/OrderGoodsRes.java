package com.xingying.shopping.master.entity.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/6/11 13:48:33
 * @description
 */
public class OrderGoodsRes implements Serializable {
    private static final long serialVersionUID = 1L;
    private String goodsId;
    private Integer goodsNum;
    private BigDecimal goodsPrice;
    private String goodsSpc;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getGoodsSpc() {
        return goodsSpc;
    }

    public void setGoodsSpc(String goodsSpc) {
        this.goodsSpc = goodsSpc;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
