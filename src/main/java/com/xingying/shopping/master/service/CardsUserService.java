package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.CardsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.CardsUserExt;

/**
 * <p>
 *  CardsUserService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
public interface CardsUserService extends IService<CardsUser> {

    PageInfo<CardsUserExt> getListByPage(PageQueryEntity<CardsUser> params);
}
