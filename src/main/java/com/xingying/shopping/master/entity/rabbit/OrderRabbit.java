package com.xingying.shopping.master.entity.rabbit;

import java.io.Serializable;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/6/12 00:53:21
 * @description
 */
public class OrderRabbit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主表ID
     */
    private List<String> orderMasterIds;

    /**
     * 用户ID
     */
    private String userId;


    public List<String> getOrderMasterIds() {
        return orderMasterIds;
    }

    public void setOrderMasterIds(List<String> orderMasterIds) {
        this.orderMasterIds = orderMasterIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
