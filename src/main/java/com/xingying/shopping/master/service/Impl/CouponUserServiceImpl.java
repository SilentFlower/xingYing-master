package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.dao.CouponMapper;
import com.xingying.shopping.master.entity.Coupon;
import com.xingying.shopping.master.entity.CouponUser;
import com.xingying.shopping.master.dao.CouponUserMapper;
import com.xingying.shopping.master.service.CouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService {

    @Autowired
    private CouponUserMapper couponUserMapper;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public PageInfo<CouponUser> getListByPage(PageQueryEntity<CouponUser> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<CouponUser> list = couponUserMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCouponUser(CouponUser couponUser) {
        Coupon coupon = couponMapper.selectById(couponUser.getCouponId());
        if (coupon.getCouponNum() > 0) {
            Boolean b = couponMapper.updateStock(couponUser.getCouponId(), 1);
            if(b){
                //库存扣成功后，正式领取
                couponUser.setGetDate(LocalDateTime.now());
                couponUser.setStatus(1);
                couponUser.setUserId(UserContext.getCurrentUser().getUserId());
                int insert = couponUserMapper.insert(couponUser);
                if (insert > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
