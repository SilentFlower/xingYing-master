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
 * 星荧商城钱包表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@TableName(value = "WALLET",schema = "XINGYING_SHOP")
public class Wallet implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 余额
     */
    @TableField("BALANCE")
    private BigDecimal balance;

    /**
     * 总收入
     */
    @TableField("WALLET_INCOME")
    private BigDecimal walletIncome;

    /**
     * 总支出
     */
    @TableField("WALLET_OUTCOME")
    private BigDecimal walletOutcome;

    /**
     * 冻结余额
     */
    @TableField("BALANCE_DISABLE")
    private BigDecimal balanceDisable;

    /**
     * 更新时间
     */
    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    public BigDecimal getBalanceDisable() {
        return balanceDisable;
    }

    public void setBalanceDisable(BigDecimal balanceDisable) {
        this.balanceDisable = balanceDisable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
            return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getWalletIncome() {
            return walletIncome;
    }

    public void setWalletIncome(BigDecimal walletIncome) {
        this.walletIncome = walletIncome;
    }

    public BigDecimal getWalletOutcome() {
            return walletOutcome;
    }

    public void setWalletOutcome(BigDecimal walletOutcome) {
        this.walletOutcome = walletOutcome;
    }

    public LocalDateTime getUpdateDate() {
            return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Wallet{" +
        "userId=" + userId +
        ", balance=" + balance +
        ", walletIncome=" + walletIncome +
        ", walletOutcome=" + walletOutcome +
        ", updateDate=" + updateDate +
        "}";
    }
}
