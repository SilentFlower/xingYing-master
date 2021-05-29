package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.GoodsDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.GoodsDetailsExt;

/**
 * <p>
 *  GoodsDetailsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
public interface GoodsDetailsService extends IService<GoodsDetails> {

    /**
     * 分页列表查询商品详细表
     * @param params
     * @return
     */
    PageInfo<GoodsDetailsExt> getListByPage(PageQueryEntity<GoodsDetails> params);

    /**
     * 新增商品详细表
     * @param goodsDetails
     * @return
     */
    boolean addGoodsDetails(GoodsDetailsExt goodsDetails);

    /**
     * 修改商品详细表
     * @param goodsDetails
     * @return
     */
    boolean editGoodsDetails(GoodsDetails goodsDetails);

    /**
     * 删除规格（包含卡密表）
     * @param goodsDetails
     * @return
     */
    boolean delGoodsDetail(GoodsDetails goodsDetails);

    /**
     * 添加卡密
     * @param goodsDetails
     * @return
     */
    boolean addGoodsCard(GoodsDetailsExt goodsDetails);
}
