package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.GoodsExt;

/**
 * <p>
 *  GoodsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
public interface GoodsService extends IService<Goods> {

    PageInfo<Goods> getListByPage(PageQueryEntity<Goods> params);

    /**
     * 新增商品
     * @param goods
     * @return
     */
    boolean addGoods(Goods goods);

    /**
     * 分页获取商品列表
     * @param param
     * @return
     */
    PageInfo<GoodsExt> getGoodsByPage(PageQueryEntity<Goods> param);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    boolean editGoods(Goods goods);

    /**
     * 删除商品表（包含详细表内容）
     * @param goodsId
     * @return
     */
    boolean delGoodss(String goodsId);
}
