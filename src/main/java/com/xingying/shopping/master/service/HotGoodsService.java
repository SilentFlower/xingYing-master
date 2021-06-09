package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Goods;
import com.xingying.shopping.master.entity.HotGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.GoodsExt;

import java.util.List;

/**
 * <p>
 *  HotGoodsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-06
 */
public interface HotGoodsService extends IService<HotGoods> {

    PageInfo<HotGoods> getListByPage(PageQueryEntity<HotGoods> params);

    /**
     * 获取所有热门商品信息
     * @return
     */
    List<GoodsExt> getHotGoods(GoodsExt goods);

    /**
     * 获取热门商品表(首页用)
     * @return
     */
    List<GoodsExt> getHotGoodsForHome();
}
