package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.CardsUser;
import com.xingying.shopping.master.entity.GoodsDetails;

/**
 * @author SiletFlower
 * @date 2021/6/14 19:12:38
 * @description
 */
public class CardsUserExt extends CardsUser {
    private String goodsId;
    private String goodsSpc;
    private String goodsName;
    private String goodsPic;
    private String card;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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
}
