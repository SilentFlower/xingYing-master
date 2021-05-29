package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  ShopService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-26
 */
public interface ShopService extends IService<Shop> {

    PageInfo<Shop> getListByPage(PageQueryEntity<Shop> params);

    /**
     * 完善或修改 星荧商城商家表
     * @param shop
     * @return
     */
    boolean editShop(Shop shop);
}
