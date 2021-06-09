package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Goods;
import com.xingying.shopping.master.entity.HotGoods;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-06
 */
@Repository
public interface HotGoodsMapper extends BaseMapper<HotGoods> {

    List<HotGoods> getListByPage(HotGoods hotGoods);

    /**
     * 获取所有热门商品
     * @return
     */
    List<GoodsExt> getHotGoods(GoodsExt goods);

    /**
     *获取热门商品表(首页用)
     * @return
     */
    List<GoodsExt> getHotGoodsForHome();
}
