package com.xingying.shopping.master.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 星荧商城卡密表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-28
 */
@TableName(value = "GOODS_CARDS",schema = "XINGYING_SHOP")
public class GoodsCards implements Serializable {

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
     * 卡密
     */
    @TableField("GOODS_CARDS")
    private String goodsCards;

    /**
     * 卡密
     */
    @TableField(exist = false)
    private String oldCards;

    /**
     * 卡密状态(1可用，0已用)
     */
    @TableField("CARDS_FLAG")
    private Integer cardsFlag;

    public String getOldCards() {
        return oldCards;
    }

    public void setOldCards(String oldCards) {
        this.oldCards = oldCards;
    }

    public String getGoodsSpc() {
            return goodsSpc;
    }

    public void setGoodsSpc(String goodsSpc) {
        this.goodsSpc = goodsSpc;
    }

    public String getGoodsCards() {
            return goodsCards;
    }

    public void setGoodsCards(String goodsCards) {
        this.goodsCards = goodsCards;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCardsFlag() {
        return cardsFlag;
    }

    public void setCardsFlag(Integer cardsFlag) {
        this.cardsFlag = cardsFlag;
    }

    @Override
    public String toString() {
        return "GoodsCards{" +
        "goodsId=" + goodsId +
        ", goodsSpc=" + goodsSpc +
        ", goodsCards=" + goodsCards +
        ", cardsFlag=" + cardsFlag +
        "}";
    }
}
