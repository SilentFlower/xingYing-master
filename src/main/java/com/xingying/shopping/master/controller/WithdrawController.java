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

import com.xingying.shopping.master.service.WithdrawService;
import com.xingying.shopping.master.entity.Withdraw;

/**
 * <p>
 * 星荧商城提现方式表（按提现时需要补齐） 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-21
 */
@RestController
@RequestMapping("${xingYing.name}/withdraw")
        public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Withdraw>> getListByPage(@RequestBody PageQueryEntity<Withdraw> params) {
        PageInfo<Withdraw> page = withdrawService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城提现方式表（按提现时需要补齐）
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getWithdrawByParam")
    public QueryResultBean<Withdraw> getWithdraw(@RequestParam String key) {
        Withdraw withdraw = withdrawService.getById(key);
        return new QueryResultBean<>(withdraw);
    }

    /**
     * 新增 星荧商城提现方式表（按提现时需要补齐）
     * @param withdraw Withdraw 对象
     * @return
     */
    @PostMapping("/addWithdraw")
    public OperationResultBean<Withdraw> addWithdraw(@RequestBody Withdraw withdraw) {
        boolean b = withdrawService.saveOrUpdate(withdraw);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(withdraw);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delWithdraws")
    public OperationResultBean<String> delWithdraws(@RequestParam List<String> keys) {
        boolean b = withdrawService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
