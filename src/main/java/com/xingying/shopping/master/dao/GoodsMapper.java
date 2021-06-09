package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Goods;
import com.xingying.shopping.master.entity.Type;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import com.xingying.shopping.master.entity.request.ClassGoodReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsExt> getListByPage(Goods goods);

    /**
     * 分页获取商品列表
     * @param data
     * @return
     */
    List<GoodsExt> getGoodsByPage(Goods data);

    /**
     * 根据Type的信息获取相应的Goods
     * @param req
     * @return
     */
    List<GoodsExt> getClassGoodsForHome(ClassGoodReq req);
}
