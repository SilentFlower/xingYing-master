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
 * 星荧虚拟商品交易系统的订单表（主表）
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@TableName(value = "ORDER_MASTER",schema = "XINGYING_SHOP")
public class OrderMaster implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "ORDER_ID", type = IdType.INPUT)
    private String orderId;

    /**
     * 付款金额
     */
    @TableField("PAY_AMOUNT")
    private BigDecimal payAmount;

    /**
     * 订单状态（0取消1创建2付款3删除4申诉5完成6待发货）
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 创建日期
     */
    @TableField("DATA_CREATE_TIME")
    private LocalDateTime dataCreateTime;

    /**
     * 修改日期
     */
    @TableField("DATA_EDIT_TIME")
    private LocalDateTime dataEditTime;

    /**
     * 订单完成日期
     */
    @TableField("DATA_COM_TIME")
    private LocalDateTime dataComTime;

    /**
     * 订单取消日期
     */
    @TableField("DATA_CAL_TIME")
    private LocalDateTime dataCalTime;

    /**
     * 对应商家ID
     */
    @TableField("SHOP_ID")
    private String shopId;

    /**
     * 申诉标志
     */
    @TableField("APPEAL_FLAG")
    private Integer appealFlag;

    /**
     * 申诉标志
     */
    @TableField("BACK_AMOUNT")
    private BigDecimal backAmount;

    public BigDecimal getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(BigDecimal backAmount) {
        this.backAmount = backAmount;
    }

    public Integer getAppealFlag() {
        return appealFlag;
    }

    public void setAppealFlag(Integer appealFlag) {
        this.appealFlag = appealFlag;
    }

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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getStatus() {
            return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public LocalDateTime getDataCreateTime() {
            return dataCreateTime;
    }

    public void setDataCreateTime(LocalDateTime dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public LocalDateTime getDataEditTime() {
            return dataEditTime;
    }

    public void setDataEditTime(LocalDateTime dataEditTime) {
        this.dataEditTime = dataEditTime;
    }

    public LocalDateTime getDataComTime() {
            return dataComTime;
    }

    public void setDataComTime(LocalDateTime dataComTime) {
        this.dataComTime = dataComTime;
    }

    public LocalDateTime getDataCalTime() {
            return dataCalTime;
    }

    public void setDataCalTime(LocalDateTime dataCalTime) {
        this.dataCalTime = dataCalTime;
    }

    @Override
    public String toString() {
        return "Order{" +
        "orderId=" + orderId +
        ", payAmount=" + payAmount +
        ", status=" + status +
        ", userId=" + userId +
        ", dataCreateTime=" + dataCreateTime +
        ", dataEditTime=" + dataEditTime +
        ", dataComTime=" + dataComTime +
        ", dataCalTime=" + dataCalTime +
        "}";
    }
}
