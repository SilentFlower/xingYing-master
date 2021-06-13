package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.CouponUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  CouponUserService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
public interface CouponUserService extends IService<CouponUser> {

    PageInfo<CouponUser> getListByPage(PageQueryEntity<CouponUser> params);

    /**
     * 领取
     * @param couponUser
     * @return
     */
    boolean addCouponUser(CouponUser couponUser);
}
