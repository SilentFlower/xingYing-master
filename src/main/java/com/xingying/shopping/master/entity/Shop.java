package com.xingying.shopping.master.entity;

import java.io.File;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * 星荧商城商家表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-26
 */
@TableName(value = "SHOP",schema = "XINGYING_SHOP")
public class Shop implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 商家ID
     */
    @TableId(value = "SHOP_ID", type = IdType.INPUT)
    private String shopId;

    /**
     * 商家店铺名
     */
    @TableField("SHOP_NAME")
    private String shopName;

    /**
     * 商家介绍
     */
    @TableField("SHOP_MEMO")
    private String shopMemo;

    /**
     * 商家创立时间
     */
    @TableField("SHOP_CREATE_DATE")
    private LocalDateTime shopCreateDate;

    /**
     * 商家公告
     */
    @TableField("SHOP_NOTICE")
    private String shopNotice;

    /**
     * 商家联系方式
     */
    @TableField("SHOP_CONTACT_TYPE")
    private String shopContactType;

    /**
     * 联系内容
     */
    @TableField("SHOP_CONTACT_CONTENT")
    private String shopContactContent;

    /**
     * 商家状态(1正常 0审核中 -1封禁)
     */
    @TableField("SHOP_STATUS")
    private Integer shopStatus;

    /**
     * 商家图片
     */
    @TableField("SHOP_PIC")
    private MultipartFile shopPic;

    /**
     * 商家图片的URL
     */
    @TableField("SHOP_PIC_URL")
    private String shopPicUrl;

    public MultipartFile getShopPic() {
        return shopPic;
    }

    public void setShopPic(MultipartFile shopPic) {
        this.shopPic = shopPic;
    }

    public String getShopPicUrl() {
        return shopPicUrl;
    }

    public void setShopPicUrl(String shopPicUrl) {
        this.shopPicUrl = shopPicUrl;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
            return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopMemo() {
            return shopMemo;
    }

    public void setShopMemo(String shopMemo) {
        this.shopMemo = shopMemo;
    }

    public LocalDateTime getShopCreateDate() {
            return shopCreateDate;
    }

    public void setShopCreateDate(LocalDateTime shopCreateDate) {
        this.shopCreateDate = shopCreateDate;
    }

    public String getShopNotice() {
            return shopNotice;
    }

    public void setShopNotice(String shopNotice) {
        this.shopNotice = shopNotice;
    }

    public String getShopContactType() {
            return shopContactType;
    }

    public void setShopContactType(String shopContactType) {
        this.shopContactType = shopContactType;
    }

    public String getShopContactContent() {
            return shopContactContent;
    }

    public void setShopContactContent(String shopContactContent) {
        this.shopContactContent = shopContactContent;
    }

    public Integer getShopStatus() {
            return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    @Override
    public String toString() {
        return "Shop{" +
        "shopId=" + shopId +
        ", shopName=" + shopName +
        ", shopMemo=" + shopMemo +
        ", shopCreateDate=" + shopCreateDate +
        ", shopNotice=" + shopNotice +
        ", shopContactType=" + shopContactType +
        ", shopContactContent=" + shopContactContent +
        ", shopStatus=" + shopStatus +
        "}";
    }
}
