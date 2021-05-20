package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.FeedBack;
import com.xingying.shopping.master.dao.FeedBackMapper;
import com.xingying.shopping.master.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@Service
public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements FeedBackService {

    @Autowired
    private FeedBackMapper feedBackMapper;

    @Override
    public PageInfo<FeedBack> getListByPage(PageQueryEntity<FeedBack> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<FeedBack> list = feedBackMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
