package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.entity.Goods;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import java.time.LocalDateTime;
import java.util.List;

import com.xingying.shopping.master.service.HotGoodsService;
import com.xingying.shopping.master.entity.HotGoods;

/**
 * <p>
 * 热门商品表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-06
 */
@RestController
@RequestMapping("${xingYing.name}/hotGoods")
        public class HotGoodsController {

    @Autowired
    private HotGoodsService hotGoodsService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<HotGoods>> getListByPage(@RequestBody PageQueryEntity<HotGoods> params) {
        PageInfo<HotGoods> page = hotGoodsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取热门商品表(首页用)
     *
     * @param
     *
     * @return Result
     */
    @GetMapping("/getHotGoodsForHome")
    public QueryResultBean<List<GoodsExt>> getHotGoodsForHome() {
        List<GoodsExt> list = hotGoodsService.getHotGoodsForHome();
        return new QueryResultBean<>(list);
    }

    /**
     * 获取热门商品表
     *
     * @param goods
     * {
     *     可传
     *     goodsName //
     *     shopName //
     *     typeId //
     * }
     * @return Result
     */
    @PostMapping("/getHotGoodsByParam")
    public QueryResultBean<List<GoodsExt>> getHotGoods(@RequestBody GoodsExt goods) {
        List<GoodsExt> list = hotGoodsService.getHotGoods(goods);
        return new QueryResultBean<>(list);
    }

    /**
     * 新增 热门商品表
     * {
     *     goodsId 传ID即可
     * }
     * @param hotGoods HotGoods 对象
     * @return
     */
    @PostMapping("/addHotGoods")
    public OperationResultBean<String> addHotGoods(@RequestBody List<HotGoods> hotGoods) {
        LocalDateTime now = LocalDateTime.now();
        boolean b = false;
        for (HotGoods hotGood : hotGoods) {
            hotGood.setAddTime(now);
            b = hotGoodsService.save(hotGood);
        }
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delHotGoods")
    public OperationResultBean<String> delHotGoods(@RequestBody List<String> keys) {
        boolean b = hotGoodsService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
