package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧虚拟商品交易系统的订单表（详细）
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@TableName(value = "ORDER_DETAIL",schema = "XINGYING_SHOP")
public class OrderDetail implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 订单详细表ID
     */
    @TableId(value = "ORDER_DETAIL_ID", type = IdType.INPUT)
    private String orderDetailId;

    /**
     * 商品ID
     */
    @TableField("GOODS_ID")
    private String goodsId;

    /**
     * 订单ID
     */
    @TableField("ORDER_ID")
    private String orderId;

    /**
     * 单价
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 数量
     */
    @TableField("TOTAL")
    private Integer total;

    /**
     * 总价
     */
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    /**
     * 商品规格
     */
    @TableField("GOODS_SPC")
    private String goodsSpc;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsSpc() {
        return goodsSpc;
    }

    public void setGoodsSpc(String goodsSpc) {
        this.goodsSpc = goodsSpc;
    }


    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
        "orderDetailId=" + orderDetailId +
        ", goodsId=" + goodsId +
        ", orderId=" + orderId +
        ", price=" + price +
        ", total=" + total +
        ", totalAmount=" + totalAmount +
        "}";
    }
}
