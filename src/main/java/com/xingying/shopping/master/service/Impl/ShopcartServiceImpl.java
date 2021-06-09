package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Shopcart;
import com.xingying.shopping.master.dao.ShopcartMapper;
import com.xingying.shopping.master.entity.ext.ShopCartExt;
import com.xingying.shopping.master.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-08
 */
@Service
public class ShopcartServiceImpl extends ServiceImpl<ShopcartMapper, Shopcart> implements ShopcartService {

    @Autowired
    private ShopcartMapper shopcartMapper;

    @Override
    public PageInfo<Shopcart> getListByPage(PageQueryEntity<Shopcart> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Shopcart> list = shopcartMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 购物车添加
     * @param shopcart
     * @return
     */
    @Override
    public boolean addShopcart(Shopcart shopcart) {
        QueryWrapper queryWrapper = getQueryWrapper(shopcart);
        Shopcart before = shopcartMapper.selectOne(queryWrapper);
        int count = 0;
        if (before == null) {
            //此前无商品
            count = shopcartMapper.insert(shopcart);
        }else{
            Long beforeNum = before.getGoodsNum();
            //此前有商品
            shopcart.setGoodsNum(beforeNum + shopcart.getGoodsNum());
            count = shopcartMapper.update(shopcart, queryWrapper);
        }
        Assert.isTrue(count > 0, "加入购物车失败");
        return true;
    }

    /**
     * 购物车修改
     * @param shopcart
     * @return
     */
    @Override
    public boolean editShopcart(Shopcart shopcart) {
        QueryWrapper queryWrapper = getQueryWrapper(shopcart);
        int update = shopcartMapper.update(shopcart, queryWrapper);
        Assert.isTrue(update > 0, "修改购物车失败");
        return true;
    }

    /**
     * 删除购物车的内容
     * @param shopcart
     * @param emptyFlag
     * @return
     */
    @Override
    public boolean delShopcarts(Shopcart shopcart, Integer emptyFlag) {
        int count = 0;
        if (emptyFlag != null && emptyFlag == 1) {
            //判断是否清空标志，如果为1则根据userID清空购物车
            count = shopcartMapper.deleteById(UserContext.getCurrentUser().getUserId());
        }else{
            //否则根据GOODS_ID和GOODS_SPC清除相应的购物车
            QueryWrapper queryWrapper = getQueryWrapper(shopcart);
            count = shopcartMapper.delete(queryWrapper);
        }
        Assert.isTrue(count > 0, "购物车删除失败");
        return true;
    }

    /**
     * 根据用户Id获取购物车
     * @return
     */
    @Override
    public List<ShopCartExt> getShopcartById() {
        return shopcartMapper.getShopcartById(UserContext.getCurrentUser().getUserId());
    }

    /**
     * 结算相关购物车
     * @param shopcarts
     * @return
     */
    @Override
    public boolean settleAccounts(List<Shopcart> shopcarts) {
        for (Shopcart shopcart : shopcarts) {
            QueryWrapper queryWrapper = getQueryWrapper(shopcart);
            shopcartMapper.delete(queryWrapper);
        }
        return true;
    }

    private QueryWrapper getQueryWrapper(Shopcart shopcart){
        shopcart.setUserId(UserContext.getCurrentUser().getUserId());
        shopcart.setCreateTime(LocalDateTime.now());
        QueryWrapper<Shopcart> queryWrapper = new QueryWrapper<Shopcart>()
                .eq("USER_ID", shopcart.getUserId())
                .eq("GOODS_ID", shopcart.getGoodsId())
                .eq("GOODS_SPC", shopcart.getGoodsSpc());
        return queryWrapper;
    }

}
