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
 * 星荧商城的钱包流水账表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@TableName(value = "WALLET_FLOW",schema = "XINGYING_SHOP")
public class WalletFlow implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 钱包交易流水号
     */
    @TableId(value = "WALLET_FLOW_ID", type = IdType.INPUT)
    private String walletFlowId;

    /**
     * 业务类型（1.充值 2.提现 3.下单）
     */
    @TableField("WALLET_FLOW_TYPE")
    private Integer walletFlowType;

    /**
     * 涉及的金额
     */
    @TableField("WALLET_FLOW_FEE")
    private BigDecimal walletFlowFee;

    /**
     * 创建时间
     */
    @TableField("WALLET_FLOW_DATE")
    private LocalDateTime walletFlowDate;

    /**
     * 状态(0正在进行，1完成，-1操作失败)
     */
    @TableField("WALLET_FLOW_STATUS")
    private Integer walletFlowStatus;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 提现方式（与提现方式表关联）
     */
    @TableField("WITHDRAW_TYPE")
    private String withdrawType;

    /**
     * 钱包余额
     */
    @TableField("WALLET_FLOW_BALANCE")
    private BigDecimal walletFlowBalance;

    public BigDecimal getWalletFlowBalance() {
        return walletFlowBalance;
    }

    public void setWalletFlowBalance(BigDecimal walletFlowBalance) {
        this.walletFlowBalance = walletFlowBalance;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletFlowId() {
        return walletFlowId;
    }

    public void setWalletFlowId(String walletFlowId) {
        this.walletFlowId = walletFlowId;
    }

    public Integer getWalletFlowType() {
            return walletFlowType;
    }

    public void setWalletFlowType(Integer walletFlowType) {
        this.walletFlowType = walletFlowType;
    }

    public BigDecimal getWalletFlowFee() {
            return walletFlowFee;
    }

    public void setWalletFlowFee(BigDecimal walletFlowFee) {
        this.walletFlowFee = walletFlowFee;
    }

    public LocalDateTime getWalletFlowDate() {
            return walletFlowDate;
    }

    public void setWalletFlowDate(LocalDateTime walletFlowDate) {
        this.walletFlowDate = walletFlowDate;
    }

    public Integer getWalletFlowStatus() {
            return walletFlowStatus;
    }

    public void setWalletFlowStatus(Integer walletFlowStatus) {
        this.walletFlowStatus = walletFlowStatus;
    }

    @Override
    public String toString() {
        return "WalletFlow{" +
        "walletFlowId=" + walletFlowId +
        ", walletFlowType=" + walletFlowType +
        ", walletFlowFee=" + walletFlowFee +
        ", walletFlowDate=" + walletFlowDate +
        ", walletFlowStatus=" + walletFlowStatus +
        "}";
    }
}
