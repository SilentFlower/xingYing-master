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

import com.xingying.shopping.master.service.WalletService;
import com.xingying.shopping.master.entity.Wallet;

/**
 * <p>
 * 星荧商城钱包表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@RestController
@RequestMapping("${xingYing.name}/wallet")
        public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Wallet>> getListByPage(@RequestBody PageQueryEntity<Wallet> params) {
        PageInfo<Wallet> page = walletService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城钱包表
     * {
     *     //GET
     *     无参
     * }
     *
     *
     * @return Result
     * {
     *   "userId": "",
     *   "balance": 0.00,//余额
     *   "walletIncome": 0.00,//支入
     *   "walletOutcome": 0.00,//支出
     *   "updateDate": "2021-05-19 23:25:47"//账户钱包更新时间
     * }
     */
    @GetMapping("/getWallet")
    public QueryResultBean<Wallet> getWallet() {
        Wallet wallet = walletService.getById(UserContext.getCurrentUser().getUserId());
        return new QueryResultBean<>(wallet);
    }

    /**
     * 新增 星荧商城钱包表
     * @param wallet Wallet 对象
     * @return
     */
    @PostMapping("/addWallet")
    public OperationResultBean<Wallet> addWallet(@RequestBody Wallet wallet) {
        boolean b = walletService.saveOrUpdate(wallet);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(wallet);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delWallets")
    public OperationResultBean<String> delWallets(@RequestParam List<String> keys) {
        boolean b = walletService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
