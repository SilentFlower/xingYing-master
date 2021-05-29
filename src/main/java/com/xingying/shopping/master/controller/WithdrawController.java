package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     *{
     *     //GET
     *     无参
     *}
     * @return Result
     * {
     *   "userId": "",
     *   "withdrawType": "",//类别
     *   "withdrawName": "",//支付宝名
     *   "withdrawAccount": ""//支付宝账号
     * }
     */
    @GetMapping("/getWithdraw")
    public QueryResultBean<Withdraw> getWithdraw() {
        //获取支付宝类的提现信息
        Withdraw withdraw = withdrawService.getOne(new QueryWrapper<Withdraw>()
                .eq("USER_ID", UserContext.getCurrentUser().getUserId())
                .eq("WITHDRAW_TYPE", "支付宝"));
        return new QueryResultBean<>(withdraw);
    }

    /**
     * {
     *     withdrawType //提款类型
     *     withdrawName //提款姓名
     *     withdrawAccount //提款账户
     *     //后台获取
     *     userId 用户ID
     * }
     * 新增 星荧商城提现方式表（按提现时需要补齐）
     * @param withdraw Withdraw 对象
     * @return
     */
    @PostMapping("/addWithdraw")
    public OperationResultBean<Withdraw> addWithdraw(@RequestBody Withdraw withdraw) {
        boolean b = false;
        withdraw.setUserId(UserContext.getCurrentUser().getUserId());
        //根据userid与type来进行插入或更新操作
        QueryWrapper<Withdraw> queryWrapper = new QueryWrapper<Withdraw>()
                .eq("USER_ID", UserContext.getCurrentUser().getUserId())
                .eq("WITHDRAW_TYPE", withdraw.getWithdrawType());
        int count = withdrawService.count(queryWrapper);
        if (count == 0) {
            b = withdrawService.save(withdraw);
        }else{
            b = withdrawService.update(withdraw, queryWrapper);
        }
        Assert.isTrue(b,"新增或修改");
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
