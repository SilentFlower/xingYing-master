package com.xingying.shopping.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
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
     * 获取热门商品表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getHotGoodsByParam")
    public QueryResultBean<HotGoods> getHotGoods(@RequestParam String key) {
        HotGoods hotGoods = hotGoodsService.getById(key);
        return new QueryResultBean<>(hotGoods);
    }

    /**
     * 新增 热门商品表
     * @param hotGoods HotGoods 对象
     * @return
     */
    @PostMapping("/addHotGoods")
    public OperationResultBean<HotGoods> addHotGoods(@RequestBody HotGoods hotGoods) {
        boolean b = hotGoodsService.saveOrUpdate(hotGoods);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(hotGoods);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delHotGoodss")
    public OperationResultBean<String> delHotGoodss(@RequestParam List<String> keys) {
        boolean b = hotGoodsService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
