package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Shopcart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.ShopCartExt;

import java.util.List;

/**
 * <p>
 *  ShopcartService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-08
 */
public interface ShopcartService extends IService<Shopcart> {

    PageInfo<Shopcart> getListByPage(PageQueryEntity<Shopcart> params);

    /**
     * 购物车添加
     * @param shopcart
     * @return
     */
    boolean addShopcart(Shopcart shopcart);

    /**
     * 购物车修改
     * @param shopcart
     * @return
     */
    boolean editShopcart(Shopcart shopcart);

    /**
     * 删除购物车的内容
     * @param shopcart
     * @param emptyFlag
     * @return
     */
    boolean delShopcarts(Shopcart shopcart, Integer emptyFlag);

    /**
     * 根据用户Id获取购物车
     */
    List<ShopCartExt> getShopcartById();

    /**
     * 结算
     * @param shopcarts
     * @return
     */
    boolean settleAccounts(List<Shopcart> shopcarts);
}
