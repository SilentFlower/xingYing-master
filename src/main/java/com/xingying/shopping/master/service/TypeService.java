package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  TypeService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
public interface TypeService extends IService<Type> {

    PageInfo<Type> getListByPage(PageQueryEntity<Type> params);
}
