package com.xingying.shopping.master.entity.ext;

import com.xingying.shopping.master.entity.Goods;

/**
 * @author SiletFlower
 * @date 2021/5/27 12:04:18
 * @description
 */
public class GoodsExt extends Goods {
    /**
     * 商品类别名
     */
    private String typeName;



    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
