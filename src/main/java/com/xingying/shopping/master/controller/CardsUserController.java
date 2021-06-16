package com.xingying.shopping.master.controller;

import com.xingying.shopping.master.entity.ext.CardsUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.CardsUserService;
import com.xingying.shopping.master.entity.CardsUser;

/**
 * <p>
 * 星荧商城用户卡密表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@RestController
@RequestMapping("${xingYing.name}/cardsUser")
        public class CardsUserController {

    @Autowired
    private CardsUserService cardsUserService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<CardsUserExt>> getListByPage(@RequestBody PageQueryEntity<CardsUser> params) {
        PageInfo<CardsUserExt> page = cardsUserService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城用户卡密表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getCardsUserByParam")
    public QueryResultBean<CardsUser> getCardsUser(@RequestParam String key) {
        CardsUser cardsUser = cardsUserService.getById(key);
        return new QueryResultBean<>(cardsUser);
    }

    /**
     * 新增 星荧商城用户卡密表
     * @param cardsUser CardsUser 对象
     * @return
     */
    @PostMapping("/addCardsUser")
    public OperationResultBean<CardsUser> addCardsUser(@RequestBody CardsUser cardsUser) {
        boolean b = cardsUserService.saveOrUpdate(cardsUser);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(cardsUser);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delCardsUsers")
    public OperationResultBean<String> delCardsUsers(@RequestParam List<String> keys) {
        boolean b = cardsUserService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
