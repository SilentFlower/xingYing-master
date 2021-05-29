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
 *
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-23
 */
@TableName(value = "LOGIN_LOG",schema = "XINGYING_SHOP")
public class LoginLog implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 登陆IP
     */
    @TableField("USER_IP")
    private String userIp;

    /**
     * 登陆地区
     */
    @TableField("USER_AREA")
    private String userArea;


    /**
     * 登陆设备
     */
    @TableField("USER_DEVICE")
    private String userDevice;

    /**
     * 登陆时间
     */
    @TableField("LOGIN_DATE")
    private LocalDateTime loginDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIp() {
            return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserArea() {
            return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public String getUserDevice () {
            return userDevice ;
    }

    public void setUserDevice (String userDevice ) {
        this.userDevice  = userDevice ;
    }

    public LocalDateTime getLoginDate() {
            return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
        "userId=" + userId +
        ", userIp=" + userIp +
        ", userArea=" + userArea +
        ", userDevice =" + userDevice  +
        ", loginDate=" + loginDate +
        "}";
    }
}
