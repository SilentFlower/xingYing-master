package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.CouponExt;

/**
 * <p>
 *  CouponService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
public interface CouponService extends IService<Coupon> {

    PageInfo<CouponExt> getListByPage(PageQueryEntity<CouponExt> params);
}
