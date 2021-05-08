package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.UserAuths;
import com.xingying.shopping.master.dao.UserAuthsMapper;
import com.xingying.shopping.master.service.UserAuthsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-04-16
 */
@Service
public class UserAuthsServiceImpl extends ServiceImpl<UserAuthsMapper, UserAuths> implements UserAuthsService {

    @Autowired
    private UserAuthsMapper userAuthsMapper;

    @Override
    public PageInfo<UserAuths> getListByPage(PageQueryEntity<UserAuths> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<UserAuths> list = userAuthsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
