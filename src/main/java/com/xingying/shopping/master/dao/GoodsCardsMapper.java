package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.GoodsCards;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-28
 */
@Repository
public interface GoodsCardsMapper extends BaseMapper<GoodsCards> {

    List<GoodsCards> getListByPage(GoodsCards goodsCards);
}
