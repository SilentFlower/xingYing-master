package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.entity.ext.GoodsDetailsExt;
import com.xingying.shopping.master.entity.request.OrderGoodsRes;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@Repository
public interface GoodsDetailsMapper extends BaseMapper<GoodsDetails> {

    /**
     * 分页列表查询商品详细表
     * @param goodsDetails
     * @return
     */
    List<GoodsDetailsExt> getListByPage(GoodsDetails goodsDetails);

    /**
     * 获取正确的价格
     * @param goods
     * @return
     */
    BigDecimal getRightPrice(OrderGoodsRes goods);

    /**
     * 对库存进行rollback操作
     * @param orderId
     * @return
     */
    Boolean rollBackByMastetId(String orderId);
}
