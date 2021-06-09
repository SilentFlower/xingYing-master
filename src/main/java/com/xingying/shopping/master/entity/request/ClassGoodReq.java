package com.xingying.shopping.master.entity.request;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/9 02:32:57
 * @description
 */
public class ClassGoodReq {
    /**
     * 类名
     */
    private String typeName;
    /**
     * 获取个数
     */
    private Integer num;
    /**
     * 商铺ID
     */
    private String shopId;
    /**
     * 类别ID
     */
    private String typeId;
    /**
     * 父类ID
     */
    private String typeParentId;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeParentId() {
        return typeParentId;
    }

    public void setTypeParentId(String typeParentId) {
        this.typeParentId = typeParentId;
    }
}
