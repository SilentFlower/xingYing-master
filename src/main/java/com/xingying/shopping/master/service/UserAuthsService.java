package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.UserAuths;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  UserAuthsService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-04-16
 */
public interface UserAuthsService extends IService<UserAuths> {

    PageInfo<UserAuths> getListByPage(PageQueryEntity<UserAuths> params);
}
