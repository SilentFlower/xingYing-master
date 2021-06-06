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
 * 星荧商城用户卡密表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@TableName(value = "CARDS_USER",schema = "XINGYING_SHOP")
public class CardsUser implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 卡密表ID
     */
    @TableField("CARD_ID")
    private String cardId;

    /**
     * 拥有时间
     */
    @TableField("GET_DATE")
    private LocalDateTime getDate;

    /**
     * 购买的订单详细ID
     */
    @TableField("ORDER_DETAIL_ID")
    private BigDecimal orderDetailId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public LocalDateTime getGetDate() {
            return getDate;
    }

    public void setGetDate(LocalDateTime getDate) {
        this.getDate = getDate;
    }

    public BigDecimal getOrderDetailId() {
            return orderDetailId;
    }

    public void setOrderDetailId(BigDecimal orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public String toString() {
        return "CardsUser{" +
        "userId=" + userId +
        ", cardId=" + cardId +
        ", getDate=" + getDate +
        ", orderDetailId=" + orderDetailId +
        "}";
    }
}
