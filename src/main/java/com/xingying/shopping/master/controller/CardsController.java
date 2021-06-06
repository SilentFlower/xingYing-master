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
import java.util.stream.Collectors;

import com.xingying.shopping.master.service.CardsService;
import com.xingying.shopping.master.entity.Cards;

/**
 * <p>
 * 星荧商城卡密表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@RestController
@RequestMapping("${xingYing.name}/cards")
        public class CardsController {

    @Autowired
    private CardsService cardsService;

    /**
     * {
     *     pageNumber:1,
     *     pageSize:10,
     *     data:{
     *         goodsId
     *         goodsSpc
     *         cardFlag
     *         goodsCards
     *     }
     * }
    * 分页列表
    *{
     *   "cardId": "",
     *   "goodsId": "",
     *   "goodsSpc": "",
     *   "goodsCards": "",
     *   "cardFlag": 0,
     *   "cardDateCreate": "2021-05-29 10:08:44"
     * }
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Cards>> getListByPage(@RequestBody PageQueryEntity<Cards> params) {
        PageInfo<Cards> page = cardsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城卡密表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getCardsByParam")
    public QueryResultBean<Cards> getCards(@RequestParam String key) {
        Cards cards = cardsService.getById(key);
        return new QueryResultBean<>(cards);
    }

    /**
     * 新增 星荧商城卡密表
     * @param cards Cards 对象
     * @return
     */
    @PostMapping("/addCards")
    public OperationResultBean<Cards> addCards(@RequestBody Cards cards) {
        boolean b = cardsService.saveOrUpdate(cards);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(cards);
    }

    /**
     * {
     *     cardId
     *     goodsCards
     * }
     * 修改 星荧商城卡密表
     * @param cards GoodsCards 对象
     * @return
     */
    @PostMapping("/editCards")
    public OperationResultBean<Cards> editGoodsCards(@RequestBody Cards cards) {
        boolean b = cardsService.updateById(cards);
        Assert.isTrue(b,"修改失败");
        return new OperationResultBean<>(cards);
    }

    /**
     * 删除
     *
     * @param cards key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delCards")
    public OperationResultBean<String> delCards(@RequestBody List<Cards> cards) {
        List<String> ids = cards.stream().map(Cards::getCardId).collect(Collectors.toList());
        boolean b = cardsService.removeByIds(ids);
        Assert.isTrue(b, "删除失败");
        //更新库存
        cardsService.updateGoods(cards.get(0));
        return new OperationResultBean<>("success");
    }
}
