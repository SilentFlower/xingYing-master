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
 * 热门商品表
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-06
 */
@TableName(value = "HOT_GOODS",schema = "XINGYING_SHOP")
public class HotGoods implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "GOODS_ID", type = IdType.INPUT)
    private String goodsId;

    /**
     * 添加时间·
     */
    @TableField("ADD_TIME")
    private LocalDateTime addTime;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public LocalDateTime getAddTime() {
            return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "HotGoods{" +
        "goodsId=" + goodsId +
        ", addTime=" + addTime +
        "}";
    }
}
