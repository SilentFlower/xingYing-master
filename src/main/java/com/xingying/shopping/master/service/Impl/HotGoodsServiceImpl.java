package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.HotGoods;
import com.xingying.shopping.master.dao.HotGoodsMapper;
import com.xingying.shopping.master.service.HotGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-06
 */
@Service
public class HotGoodsServiceImpl extends ServiceImpl<HotGoodsMapper, HotGoods> implements HotGoodsService {

    @Autowired
    private HotGoodsMapper hotGoodsMapper;

    @Override
    public PageInfo<HotGoods> getListByPage(PageQueryEntity<HotGoods> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<HotGoods> list = hotGoodsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
