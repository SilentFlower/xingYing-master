package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.entity.ext.ShopCartExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.ShopcartService;
import com.xingying.shopping.master.entity.Shopcart;

/**
 * <p>
 * 商品购物车 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-06-08
 */
@RestController
@RequestMapping("${xingYing.name}/shopcart")
        public class ShopcartController {

    @Autowired
    private ShopcartService shopcartService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Shopcart>> getListByPage(@RequestBody PageQueryEntity<Shopcart> params) {
        PageInfo<Shopcart> page = shopcartService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 根据用户Id获取购物车
     *
     * @return Result
     */
    @GetMapping("/getShopcartById")
    public QueryResultBean<List<ShopCartExt>> getShopcartById() {
        List<ShopCartExt> list = shopcartService.getShopcartById();
        return new QueryResultBean<>(list);
    }

    /**
     * 购物车内修改数量
     * @param shopcart Shopcart 对象
     * @return
     */
    @PostMapping("/editShopcart")
    public OperationResultBean<Shopcart> editShopcart(@RequestBody Shopcart shopcart) {
        shopcartService.editShopcart(shopcart);
        return new OperationResultBean<>(shopcart);
    }

    /**
     * 购物车添加
     * @param shopcart Shopcart 对象
     * @return
     */
    @PostMapping("/addShopcart")
    public OperationResultBean<Shopcart> addShopcart(@RequestBody Shopcart shopcart) {
        boolean b = shopcartService.addShopcart(shopcart);
        return new OperationResultBean<>(shopcart);
    }

    /**
     * 删除购物车的内容
     * @param shopcart
     * @param emptyFlag
     * @return
     */
    @DeleteMapping("/delShopcart")
    public OperationResultBean<String> delShopcarts(Shopcart shopcart,
                                                    Integer emptyFlag) {
        boolean b = shopcartService.delShopcarts(shopcart,emptyFlag);
        return new OperationResultBean<>("success");
    }

    /**
     * 结算后清空选择的
     *
     * @return
     */
    @PostMapping("/settleAccounts")
    public OperationResultBean<String> settleAccounts(@RequestBody List<Shopcart> shopcarts) {
        boolean b = shopcartService.settleAccounts(shopcarts);
        return new OperationResultBean<>("success");
    }
}
