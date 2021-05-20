package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Withdraw;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  WithdrawService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-21
 */
public interface WithdrawService extends IService<Withdraw> {

    PageInfo<Withdraw> getListByPage(PageQueryEntity<Withdraw> params);
}
