package com.xingying.shopping.master.controller;

import com.xingying.shopping.master.common.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.ShopService;
import com.xingying.shopping.master.entity.Shop;

/**
 * <p>
 * 星荧商城商家表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-26
 */
@RestController
@RequestMapping("${xingYing.name}/shop")
        public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Shop>> getListByPage(@RequestBody PageQueryEntity<Shop> params) {
        PageInfo<Shop> page = shopService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * {
     *     无参
     * }
     * 获取星荧商城商家表
     *
     * @return Result
     */
    @GetMapping("/getShopInfo")
    public QueryResultBean<Shop> getShopInfo() {
        Shop shop = shopService.getById(UserContext.getCurrentUser().getUserId());
        return new QueryResultBean<>(shop);
    }

    /**
     * 获取星荧商城商家表
     *
     * @return Result
     */
    @GetMapping("/getShop")
    public QueryResultBean<Shop> getShopInfo(String shopId) {
        Shop shop = shopService.getById(shopId);
        return new QueryResultBean<>(shop);
    }

    /**
     * 新增 星荧商城商家表
     * @param shop Shop 对象
     * @return
     */
    @PostMapping("/addShop")
    public OperationResultBean<Shop> addShop(@RequestBody Shop shop) {
        boolean b = shopService.saveOrUpdate(shop);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(shop);
    }

    /**
     * 完善或修改 星荧商城商家表
     * {
     *    //可传
     *    shopName //商家名
     *    shopMemo //商家备注
     *    shopNotice //商家公告
     *    shopContactContent //联系方式内容
     *    //后台获取
     *    shopId //商家ID
     *
     * }
     * @param shop Shop 对象
     * @return
     */
    @PostMapping("/editShop")
    public OperationResultBean<Shop> editShop(Shop shop) {
        boolean b = shopService.editShop(shop);
        Assert.isTrue(b,"信息更新失败");
        return new OperationResultBean<>(shop);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delShops")
    public OperationResultBean<String> delShops(@RequestParam List<String> keys) {
        boolean b = shopService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
