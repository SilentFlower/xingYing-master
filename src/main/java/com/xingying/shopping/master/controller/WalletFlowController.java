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

import com.xingying.shopping.master.service.WalletFlowService;
import com.xingying.shopping.master.entity.WalletFlow;

/**
 * <p>
 * 星荧商城的钱包流水账表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@RestController
@RequestMapping("${xingYing.name}/walletFlow")
        public class WalletFlowController {

    @Autowired
    private WalletFlowService walletFlowService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<WalletFlow>> getListByPage(@RequestBody PageQueryEntity<WalletFlow> params) {
        PageInfo<WalletFlow> page = walletFlowService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城的钱包流水账表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getWalletFlowByParam")
    public QueryResultBean<WalletFlow> getWalletFlow(@RequestParam String key) {
        WalletFlow walletFlow = walletFlowService.getById(key);
        return new QueryResultBean<>(walletFlow);
    }

    /**
     * 新增 星荧商城的钱包流水账表
     * @param walletFlow WalletFlow 对象
     * @return
     */
    @PostMapping("/addWalletFlow")
    public OperationResultBean<WalletFlow> addWalletFlow(@RequestBody WalletFlow walletFlow) {
        boolean b = walletFlowService.saveOrUpdate(walletFlow);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(walletFlow);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delWalletFlows")
    public OperationResultBean<String> delWalletFlows(@RequestParam List<String> keys) {
        boolean b = walletFlowService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 充值
     *{
     *     walletFlowFee//充值金额
     *     //后台获取
     *     walletFlowType//类型 1.充值
     *     userId//用户ID
     *     walletFlowId//流水ID
     *     walletFlowDate//创建时间
     *     walletFlowStatus//状态(0正在进行，1完成，-1操作失败)
     *
     *}
     * @param walletFlow key字符串，根据,号分隔
     * @return Result
     * {
     *     walletFlowId//流水ID
     * }
     */
    @PostMapping("/recharge")
    public OperationResultBean<String> recharge(@RequestBody WalletFlow walletFlow) {
        String walletFlowId = walletFlowService.recharge(walletFlow);
        return new OperationResultBean<>(walletFlowId);
    }

    /**
     * 提现预检查
     *{
     *     //GET
     *     无参
     *
     *}
     * @return Result
     * {
     *     String//信息
     * }
     */
    @GetMapping("/withdrawPre")
    public QueryResultBean<String> withdrawPre() {
        String msg = walletFlowService.withdrawPre();
        return new QueryResultBean<>(msg);
    }

    /**
     * 提现
     *{
     *     walletFlowFee//充值金额
     *     //后台获取
     *     walletFlowType//类型 2.提现
     *     userId//用户ID
     *     walletFlowId//流水ID
     *     walletFlowDate//创建时间
     *     walletFlowStatus//状态(0正在进行，1完成，-1操作失败)
     *
     *}
     * @param walletFlow key字符串，根据,号分隔
     * @return Result
     * {
     *     walletFlowId//流水ID
     * }
     */
    @PostMapping("/withdraw")
    public OperationResultBean<String> withdraw(@RequestBody WalletFlow walletFlow) {
        String walletFlowId = walletFlowService.withdraw(walletFlow);
        return new OperationResultBean<>(walletFlowId);
    }

    /**
     * 下单
     *{
     *     walletFlowFee//充值金额
     *     //后台获取
     *     walletFlowType//类型 3.下单
     *     userId//用户ID
     *     walletFlowId//流水ID
     *     walletFlowDate//创建时间
     *     walletFlowStatus//状态(0正在进行，1完成，-1操作失败)
     *
     *}
     * @param walletFlow key字符串，根据,号分隔
     * @return Result
     * {
     *     walletFlowId//流水ID
     * }
     */
    @PostMapping("/placeAnOrder")
    public OperationResultBean<String> placeAnOrder(@RequestBody WalletFlow walletFlow) {
        String walletFlowId = walletFlowService.placeAnOrder(walletFlow);
        return new OperationResultBean<>(walletFlowId);
    }
}
