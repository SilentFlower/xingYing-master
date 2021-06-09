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
 * 商品购物车
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-08
 */
@TableName(value = "SHOPCART",schema = "XINGYING_SHOP")
public class Shopcart implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 商品
     */
    @TableField("GOODS_ID")
    private String goodsId;

    /**
     * 商品规格
     */
    @TableField("GOODS_SPC")
    private String goodsSpc;

    /**
     * 购物车数量
     */
    @TableField("GOODS_NUM")
    private Long goodsNum;

    /**
     * 加入购物车时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Long getGoodsNum() {
            return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public LocalDateTime getCreateTime() {
            return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shopcart{" +
        "userId=" + userId +
        ", goodsId=" + goodsId +
        ", goodsSpc=" + goodsSpc +
        ", goodsNum=" + goodsNum +
        ", createTime=" + createTime +
        "}";
    }
}
