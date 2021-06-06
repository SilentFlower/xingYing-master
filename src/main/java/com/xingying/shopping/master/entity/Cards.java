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
 * 星荧商城卡密表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@TableName(value = "CARDS",schema = "XINGYING_SHOP")
public class Cards implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 卡密ID
     */
    @TableId(value = "CARD_ID", type = IdType.INPUT)
    private String cardId;

    /**
     * 商品ID
     */
    @TableField("GOODS_ID")
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
     * 卡密状态(1可用，0已用)
     */
    @TableField("CARD_FLAG")
    private Integer cardFlag;

    /**
     * 创建时间
     */
    @TableField("CARD_DATE_CREATE")
    private LocalDateTime cardDateCreate;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getGoodsCards() {
            return goodsCards;
    }

    public void setGoodsCards(String goodsCards) {
        this.goodsCards = goodsCards;
    }

    public Integer getCardFlag() {
            return cardFlag;
    }

    public void setCardFlag(Integer cardFlag) {
        this.cardFlag = cardFlag;
    }

    public LocalDateTime getCardDateCreate() {
            return cardDateCreate;
    }

    public void setCardDateCreate(LocalDateTime cardDateCreate) {
        this.cardDateCreate = cardDateCreate;
    }

    @Override
    public String toString() {
        return "Cards{" +
        "cardId=" + cardId +
        ", goodsId=" + goodsId +
        ", goodsSpc=" + goodsSpc +
        ", goodsCards=" + goodsCards +
        ", cardFlag=" + cardFlag +
        ", cardDateCreate=" + cardDateCreate +
        "}";
    }
}
