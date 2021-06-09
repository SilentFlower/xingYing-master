package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Shopcart;
import com.xingying.shopping.master.entity.ext.ShopCartExt;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-08
 */
@Repository
public interface ShopcartMapper extends BaseMapper<Shopcart> {

    List<Shopcart> getListByPage(Shopcart shopcart);

    /**
     * 根据用户Id获取购物车
     * @param userId
     * @return
     */
    List<ShopCartExt> getShopcartById(String userId);
}
