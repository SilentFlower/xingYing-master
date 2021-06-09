package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Coupon;
import com.xingying.shopping.master.dao.CouponMapper;
import com.xingying.shopping.master.entity.ext.CouponExt;
import com.xingying.shopping.master.entity.request.CouponRes;
import com.xingying.shopping.master.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public PageInfo<CouponExt> getListByPage(PageQueryEntity<CouponExt> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<CouponExt> list = couponMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     *  结算界面查找可使用的
     * @param cuponRes
     * @return
     */
    @Override
    public List<CouponExt> getCanUseCoupon(CouponRes cuponRes) {
        cuponRes.setUserId(UserContext.getCurrentUser().getUserId());
        List<CouponExt> list = couponMapper.getCanUseCoupon(cuponRes);
        return list;
    }
}
