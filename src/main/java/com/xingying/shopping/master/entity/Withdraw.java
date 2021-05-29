package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧商城提现方式表（按提现时需要补齐）
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-21
 */
@TableName(value = "WITHDRAW",schema = "XINGYING_SHOP")
public class Withdraw implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 提现方式（支付宝）
     */
    @TableField("WITHDRAW_TYPE")
    private String withdrawType;

    /**
     * 提现姓名（支付宝）
     */
    @TableField("WITHDRAW_NAME")
    private String withdrawName;

    /**
     * 提现到的账户（支付宝）
     */
    @TableField("WITHDRAW_ACCOUNT")
    private String withdrawAccount;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWithdrawType() {
            return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getWithdrawName() {
            return withdrawName;
    }

    public void setWithdrawName(String withdrawName) {
        this.withdrawName = withdrawName;
    }

    public String getWithdrawAccount() {
            return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
        "userId=" + userId +
        ", withdrawType=" + withdrawType +
        ", withdrawName=" + withdrawName +
        ", withdrawAccount=" + withdrawAccount +
        "}";
    }
}
