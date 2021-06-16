package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Cards;
import com.xingying.shopping.master.entity.CardsUser;
import com.xingying.shopping.master.dao.CardsUserMapper;
import com.xingying.shopping.master.entity.ext.CardsUserExt;
import com.xingying.shopping.master.service.CardsUserService;
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
public class CardsUserServiceImpl extends ServiceImpl<CardsUserMapper, CardsUser> implements CardsUserService {

    @Autowired
    private CardsUserMapper cardsUserMapper;

    @Override
    public PageInfo<CardsUserExt> getListByPage(PageQueryEntity<CardsUser> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        CardsUser data = pageQueryEntity.getData();
        if (data == null) {
            data = new CardsUser();
        }
        data.setUserId(UserContext.getCurrentUser().getUserId());
        List<CardsUserExt> list = cardsUserMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }
}
