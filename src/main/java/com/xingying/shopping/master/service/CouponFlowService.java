package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.CouponFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  CouponFlowService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
public interface CouponFlowService extends IService<CouponFlow> {

    PageInfo<CouponFlow> getListByPage(PageQueryEntity<CouponFlow> params);
}
