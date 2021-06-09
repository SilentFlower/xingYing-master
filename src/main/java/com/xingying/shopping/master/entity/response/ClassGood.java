package com.xingying.shopping.master.entity.response;

import com.xingying.shopping.master.entity.ext.GoodsExt;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/7 12:37:28
 * 首页展示的类别代码
 * @description
 */
public class ClassGood {
    /**
     * 大类别图片
     */
    private String typePic;

    /**
     * 展示的信息
     */
    private List<GoodsExt> goods;

    public String getTypePic() {
        return typePic;
    }

    public void setTypePic(String typePic) {
        this.typePic = typePic;
    }

    public List<GoodsExt> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsExt> goods) {
        this.goods = goods;
    }
}
