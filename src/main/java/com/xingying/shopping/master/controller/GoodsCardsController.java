package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.GoodsCardsService;
import com.xingying.shopping.master.entity.GoodsCards;

/**
 * <p>
 * 星荧商城卡密表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-28
 */
@RestController
@RequestMapping("${xingYing.name}/goodsCards")
        public class GoodsCardsController {

    @Autowired
    private GoodsCardsService goodsCardsService;

    /**
     * {
     *     pageNumber:1,
     *     pageSize:10,
     *     data:{
     *         goodsId
     *         goodsSpc
     *         cardsFlag
     *     }
     * }
    * 分页列表
    *{
     *   "goodsId": "",
     *   "goodsSpc": "",
     *   "goodsCards": "",
     *   "cardsFlag": 0
     * }
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<GoodsCards>> getListByPage(@RequestBody PageQueryEntity<GoodsCards> params) {
        PageInfo<GoodsCards> page = goodsCardsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城卡密表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getGoodsCardsByParam")
    public QueryResultBean<GoodsCards> getGoodsCards(@RequestParam String key) {
        GoodsCards goodsCards = goodsCardsService.getById(key);
        return new QueryResultBean<>(goodsCards);
    }

    /**
     * 新增 星荧商城卡密表
     * @param goodsCards GoodsCards 对象
     * @return
     */
    @PostMapping("/addGoodsCards")
    public OperationResultBean<GoodsCards> addGoodsCards(@RequestBody GoodsCards goodsCards) {
        boolean b = goodsCardsService.saveOrUpdate(goodsCards);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(goodsCards);
    }

    /**
     * {
     *     goodsId
     *     goodsSpc
     *     goodsCards
     *     oldCards
     * }
     * 新增 星荧商城卡密表
     * @param goodsCards GoodsCards 对象
     * @return
     */
    @PostMapping("/editGoodsCards")
    public OperationResultBean<GoodsCards> editGoodsCards(@RequestBody GoodsCards goodsCards) {
        boolean b = goodsCardsService.update(goodsCards, new QueryWrapper<GoodsCards>()
                .eq("GOODS_ID", goodsCards.getGoodsId())
                .eq("GOODS_SPC", goodsCards.getGoodsSpc())
                .eq("GOODS_CARDS", goodsCards.getOldCards()));
        Assert.isTrue(b,"修改失败");
        return new OperationResultBean<>(goodsCards);
    }

    /**
     * 删除
     *
     * @param  goodsCard
     * @return Result
     */
    @PostMapping("/delGoodsCard")
    public OperationResultBean<String> delGoodsCard(@RequestBody GoodsCards goodsCard) {
        goodsCardsService.delGoodsCard(goodsCard);
        //更新库存
        goodsCardsService.updateGoods(goodsCard);
        return new OperationResultBean<>("success");
    }

    /**
     * 批量删除
     *
     * @param  goodsCards
     * @return Result
     */
    @PostMapping("/delGoodsCards")
    public OperationResultBean<String> delGoodsCards(@RequestBody List<GoodsCards> goodsCards) {
        for (GoodsCards goodsCard : goodsCards) {
            goodsCardsService.delGoodsCard(goodsCard);
        }
        //更新库存
        goodsCardsService.updateGoods(goodsCards.get(0));
        return new OperationResultBean<>("success");
    }
}
