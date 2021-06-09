package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.entity.ext.GoodsDetailsExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.GoodsDetailsService;
import com.xingying.shopping.master.entity.GoodsDetails;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@RestController
@RequestMapping("${xingYing.name}/goodsDetails")
        public class GoodsDetailsController {

    @Autowired
    private GoodsDetailsService goodsDetailsService;

    /**
    * 分页列表查询商品详细表
     * {
     *     pageSize
     *     pageNumber
     *     data:{
     *         goodsId
     *     }
     * }
     *
     * {
     *   "goodsName": "",
     *   "goodsId": "",
     *   "goodsSpc": "",
     *   "goodsPrice": 0.00,
     *   "goodsNum": 0,
     *   "goodsAuto": 0
     * }
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<GoodsDetailsExt>> getListByPage(@RequestBody PageQueryEntity<GoodsDetails> params) {
        PageInfo<GoodsDetailsExt> page = goodsDetailsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 根据各类条件获取某一商品详细
     *
     * @param goodsId
     * @return Result
     */
    @GetMapping("/getGoodsDetailsByParam")
    public QueryResultBean<List<GoodsDetails>> getGoodsDetails(String goodsId) {
        QueryWrapper<GoodsDetails> queryWrapper = new QueryWrapper<>();
        if (goodsId != null && goodsId != "") {
            queryWrapper.eq("GOODS_ID", goodsId);
        }
        List<GoodsDetails> list = goodsDetailsService.list(queryWrapper);
        return new QueryResultBean<>(list);
    }

    /**
     * 新增商品详细表
     * {
     *   "goodsId": "",
     *   "goodsSpc": "",
     *   "goodsPrice": 0.00,
     *   "goodsNum": 0,
     *   "goodsAuto": 0
     *   "cards"
     * }
     * @param goodsDetails GoodsDetails 对象
     * @return
     */
    @PostMapping("/addGoodsDetails")
    public OperationResultBean<GoodsDetails> addGoodsDetails(@RequestBody GoodsDetailsExt goodsDetails) {
        boolean b = goodsDetailsService.addGoodsDetails(goodsDetails);
        return new OperationResultBean<>(goodsDetails);
    }

    /**
     * 新增卡密
     * {
     *   "goodsId": "",
     *   "goodsSpc": "",
     *   "cards"
     * }
     * @param goodsDetails GoodsDetails 对象
     * @return
     */
    @PostMapping("/addGoodsCard")
    public OperationResultBean<GoodsDetails> addGoodsCard(@RequestBody GoodsDetailsExt goodsDetails) {
        boolean b = goodsDetailsService.addGoodsCard(goodsDetails);
        return new OperationResultBean<>(goodsDetails);
    }

    /**
     * 修改商品详细表
     * {
     *   "goodsId": "",//不可改变
     *   "goodsSpc": "",//不可改变
     *   "goodsPrice": 0.00,
     *   "goodsNum": 0,
     *   "goodsAuto": 0
     * }
     * @param goodsDetails GoodsDetails 对象
     * @return
     */
    @PostMapping("/editGoodsDetails")
    public OperationResultBean<GoodsDetails> editGoodsDetails(@RequestBody GoodsDetails goodsDetails) {
        boolean b = goodsDetailsService.editGoodsDetails(goodsDetails);
        return new OperationResultBean<>(goodsDetails);
    }

    /**
     * 删除
     * {
     *     goodsId
     *     goodsSpc
     * }
     *
     * @param goodsDetails key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delGoodsDetails")
    public OperationResultBean<String> delGoodsDetails(@RequestBody List<GoodsDetails> goodsDetails) {
        for (GoodsDetails details : goodsDetails) {
            goodsDetailsService.delGoodsDetail(details);
        }
        return new OperationResultBean<>("success");
    }

    /**
     * 删除
     * {
     *     goodsId
     *     goodsSpc
     * }
     *
     * @param goodsDetail key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delGoodsDetail")
    public OperationResultBean<String> delGoodsDetail(@RequestBody GoodsDetails goodsDetail) {
        boolean b = goodsDetailsService.delGoodsDetail(goodsDetail);
        return new OperationResultBean<>("success");
    }
}
