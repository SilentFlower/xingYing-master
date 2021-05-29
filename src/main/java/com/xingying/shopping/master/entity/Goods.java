package com.xingying.shopping.master.entity;

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
 * 星荧虚拟商品交易系统的商品表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@TableName(value = "GOODS",schema = "XINGYING_SHOP")
public class Goods implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "GOODS_ID", type = IdType.INPUT)
    private String goodsId;

    /**
     * 商品名
     */
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品图片
     */
    @TableField(exist = false)
    private MultipartFile pic;

    /**
     * 商品图片存放的位置
     */
    @TableField("GOODS_PIC")
    private String goodsPic;

    /**
     * 创建时间
     */
    @TableField("DATA_CREATE_TIME")
    private LocalDateTime dataCreateTime;

    /**
     * 上次修改时间
     */
    @TableField("DATA_EDIT_TIME")
    private LocalDateTime dataEditTime;

    /**
     * 商品类别
     */
    @TableField("TYPE_ID")
    private String typeId;

    /**
     * 状态(0未启用1启用)
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 商家ID
     */
    @TableField("SHOP_ID")
    private String shopId;

    /**
     * 商品描述
     */
    @TableField("GOODS_MEMO")
    private String goodsMemo;

    public String getGoodsMemo() {
        return goodsMemo;
    }

    public void setGoodsMemo(String goodsMemo) {
        this.goodsMemo = goodsMemo;
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGoodsName() {
            return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
            return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
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


    public Integer getStatus() {
            return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Goods{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsPic=" + goodsPic +
        ", dataCreateTime=" + dataCreateTime +
        ", dataEditTime=" + dataEditTime +
        ", typeId=" + typeId +
        ", status=" + status +
        ", shopId=" + shopId +
        "}";
    }
}
