package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Withdraw;
import com.xingying.shopping.master.dao.WithdrawMapper;
import com.xingying.shopping.master.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-21
 */
@Service
public class WithdrawServiceImpl extends ServiceImpl<WithdrawMapper, Withdraw> implements WithdrawService {

    @Autowired
    private WithdrawMapper withdrawMapper;

    @Override
    public PageInfo<Withdraw> getListByPage(PageQueryEntity<Withdraw> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Withdraw> list = withdrawMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
