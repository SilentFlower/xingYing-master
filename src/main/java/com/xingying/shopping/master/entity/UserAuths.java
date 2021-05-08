package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧商城用户第三方授权信息表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-04-16
 */
@TableName(value = "USER_AUTHS",schema = "XINGYING_SHOP")
public class UserAuths implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 第三方类型
     */
    @TableField("IDENTITY_TYPE")
    private String identityType;

    /**
     * 第三方唯一标识
     */
    @TableId(value = "IDENTIFIER", type = IdType.INPUT)
    private String identifier;

    /**
     * 密码凭证或token（如果有）
     */
    @TableField("CREDENTIAL")
    private String credential;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdentityType() {
            return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
            return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
            return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "UserAuths{" +
        "userId=" + userId +
        ", identityType=" + identityType +
        ", identifier=" + identifier +
        ", credential=" + credential +
        "}";
    }
}
