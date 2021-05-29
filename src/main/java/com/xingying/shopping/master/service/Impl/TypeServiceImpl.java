package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Type;
import com.xingying.shopping.master.dao.TypeMapper;
import com.xingying.shopping.master.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> getListByPage(PageQueryEntity<Type> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Type> list = typeMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
