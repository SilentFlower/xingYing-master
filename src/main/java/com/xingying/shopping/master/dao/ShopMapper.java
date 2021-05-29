package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Shop;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-26
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

    List<Shop> getListByPage(Shop shop);
}
