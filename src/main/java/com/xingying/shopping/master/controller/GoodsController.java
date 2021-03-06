package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.GoodsService;
import com.xingying.shopping.master.entity.Goods;

/**
 * <p>
 * 星荧虚拟商品交易系统的商品表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@RestController
@RequestMapping("${xingYing.name}/goods")
        public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
    * 分页列表
     * {
     *     pageSize
     *     pageNumber
     *     data:{
     *      可传
     *      goodsName //
     *      shopName //
     *      typeId //
     *      shopId
     *     }
     *
     * }
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<GoodsExt>> getListByPage(@RequestBody PageQueryEntity<GoodsExt> params) {
        PageInfo<GoodsExt> page = goodsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧虚拟商品交易系统的商品表
     *
     * @param typeId
     * @return Result
     */
    @GetMapping("/getGoodsByType")
    public QueryResultBean<List<Goods>> getGoodsByType(@RequestParam String typeId) {
        List<Goods> list = goodsService.list(new QueryWrapper<Goods>()
                .eq("TYPE_ID", typeId)
                .eq("STATUS", 1));
        return new QueryResultBean<>(list);
    }

    /**
     * 根据各类条件获取某一商品
     *
     * @param goodsId
     * @return Result
     */
    @GetMapping("/getGoodsByParam")
    public QueryResultBean<Goods> getGoodsByParam(String goodsId) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>()
                .eq("STATUS", 1);
        if (goodsId != null && goodsId != "") {
            queryWrapper.eq("GOODS_ID", goodsId);
        }
        Goods one = goodsService.getOne(queryWrapper);
        return new QueryResultBean<>(one);
    }

    /**
     * 新增商品
     * {
     *   //传
     *   "typeId": "",
     *   "goodsName": "",
     *   "pic": {},
     *   "goodsMemo":"",
     *   //后台获取
     *   "goodsId": "",
     *   "goodsPic": "",
     *   "dataCreateTime": "2021-05-27 09:18:06",
     *   "status": 0,
     *   "shopId": ""
     * }
     * @param goods Goods 对象
     * @return
     */
    @PostMapping("/addGoods")
    public OperationResultBean<Goods> addGoods(Goods goods) {
        boolean b = goodsService.addGoods(goods);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(goods);
    }

    /**
     * 修改商品
     * {
     *   //传
     *   "goodsId": "",
     *   "typeId": "",
     *   "goodsName": "",
     *   "goodsMemo":"",
     *   "pic": {},
     *   //后台获取
     *   "dataCreateTime": "2021-05-27 09:18:06",
     * }
     * @param goods Goods 对象
     * @return
     */
    @PostMapping("/editGoods")
    public OperationResultBean<Goods> editGoods(Goods goods) {
        boolean b = goodsService.editGoods(goods);
        Assert.isTrue(b,"修改失败");
        return new OperationResultBean<>(goods);
    }

    /**
     * 分页获取商品列表
     * {
     *     pageNumber
     *     pageSize
     *     data:{
     *         shopId
     *     }
     * }
     * @return
     * {
     *   "typeName": "",
     *   "goodsId": "",
     *   "goodsMemo":"",
     *   "goodsName": "",
     *   "goodsPic": "",
     *   "dataCreateTime": "2021-05-27 09:18:06",
     *   "dataEditTime": "2021-05-27 09:18:06",
     *   "typeId": "",
     *   "status": 0,
     *   "shopId": ""
     * }
     */
    @PostMapping("/getGoodsByPage")
    public QueryResultBean<PageInfo<GoodsExt>> getGoodsByPage(@RequestBody PageQueryEntity<Goods> param){
        PageInfo<GoodsExt> info = goodsService.getGoodsByPage(param);
        return new QueryResultBean<>(info);
    }

    /**
     * {
     *     goodsId
     * }
     * 删除商品（包含商品详细表）
     *
     * @return Result
     */
    @PostMapping("/delGoods")
    public OperationResultBean<String> delGoodss(String goodsId) {
        boolean b = goodsService.delGoodss(goodsId);
        return new OperationResultBean<>("success");
    }
}
