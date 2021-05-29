package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.entity.ext.GoodsDetailsExt;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
