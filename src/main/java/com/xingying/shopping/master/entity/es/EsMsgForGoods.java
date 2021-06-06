package com.xingying.shopping.master.entity.es;

import com.xingying.shopping.master.entity.Goods;

import java.io.Serializable;

/**
 * @author SiletFlower
 * @date 2021/5/31 09:53:25
 * ES商品
 * @description
 */
public class EsMsgForGoods implements Serializable {
    private static final long serialVersionUID = 3572599349158869479L;

    /**
     * 新增或修改
     */
    public final static String CREATE_OR_UPDATE = "create_or_update";

    /**
     * 删除
     */
    public final static String REMOVE = "remove";

    /**
     * 商品
     */
    private EsGoods esGoods;

    public EsGoods getEsGoods() {
        return esGoods;
    }

    public void setEsGoods(EsGoods esGoods) {
        this.esGoods = esGoods;
    }
}
