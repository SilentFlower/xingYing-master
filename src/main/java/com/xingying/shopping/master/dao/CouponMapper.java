package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Coupon;
import com.xingying.shopping.master.entity.ext.CouponExt;
import com.xingying.shopping.master.entity.request.CouponRes;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@Repository
public interface CouponMapper extends BaseMapper<Coupon> {

    List<CouponExt> getListByPage(CouponExt coupon);

    /**
     *  结算界面查找可使用的
     * @param cuponRes
     * @return
     */
    List<CouponExt> getCanUseCoupon(CouponRes cuponRes);

    /**
     *
     * @param couponId 领取的优惠券ID
     * @param num 领取的数量
     */
    Boolean updateStock(String couponId, int num);
}
