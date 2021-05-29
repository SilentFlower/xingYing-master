package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  LoginLogService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-23
 */
public interface LoginLogService extends IService<LoginLog> {

    PageInfo<LoginLog> getListByPage(PageQueryEntity<LoginLog> params);
}
