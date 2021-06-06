package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.CouponFlow;
import com.xingying.shopping.master.dao.CouponFlowMapper;
import com.xingying.shopping.master.service.CouponFlowService;
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
public class CouponFlowServiceImpl extends ServiceImpl<CouponFlowMapper, CouponFlow> implements CouponFlowService {

    @Autowired
    private CouponFlowMapper couponFlowMapper;

    @Override
    public PageInfo<CouponFlow> getListByPage(PageQueryEntity<CouponFlow> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<CouponFlow> list = couponFlowMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
