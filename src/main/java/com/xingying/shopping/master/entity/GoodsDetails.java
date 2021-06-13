package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@TableName(value = "GOODS_DETAILS",schema = "XINGYING_SHOP")
public class GoodsDetails implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "GOODS_ID", type = IdType.INPUT)
    private String goodsId;

    /**
     * 商品规格
     */
    @TableField("GOODS_SPC")
    private String goodsSpc;

    /**
     * 商品价格
     */
    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    /**
     * 商品库存
     */
    @TableField("GOODS_NUM")
    private Long goodsNum;

    /**
     * 是否为卡密
     */
    @TableField("GOODS_AUTO")
    private Integer goodsAuto;

    public Integer getGoodsAuto() {
        return goodsAuto;
    }

    public void setGoodsAuto(Integer goodsAuto) {
        this.goodsAuto = goodsAuto;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpc() {
            return goodsSpc;
    }

    public void setGoodsSpc(String goodsSpc) {
        this.goodsSpc = goodsSpc;
    }

    public BigDecimal getGoodsPrice() {
            return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsNum() {
            return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "GoodsDetails{" +
        "goodsId=" + goodsId +
        ", goodsSpc=" + goodsSpc +
        ", goodsPrice=" + goodsPrice +
        ", goodsNum=" + goodsNum +
        "}";
    }
}
