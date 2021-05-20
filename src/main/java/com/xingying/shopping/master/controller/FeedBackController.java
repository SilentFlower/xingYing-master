package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.xingying.shopping.master.service.FeedBackService;
import com.xingying.shopping.master.entity.FeedBack;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户反馈表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@RestController
@RequestMapping("${xingYing.name}/feedBack")
        public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<FeedBack>> getListByPage(@RequestBody PageQueryEntity<FeedBack> params) {
        PageInfo<FeedBack> page = feedBackService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取用户反馈表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getFeedBackByParam")
    public QueryResultBean<FeedBack> getFeedBack(@RequestParam String key) {
        FeedBack feedBack = feedBackService.getById(key);
        return new QueryResultBean<>(feedBack);
    }

    /**
     * 新增 用户反馈表
     * @param feedBack FeedBack 对象
     * @return
     */
    @PostMapping("/addFeedBack")
    public OperationResultBean<FeedBack> addFeedBack(@RequestBody FeedBack feedBack) {
        feedBack.setUserId(UserContext.getCurrentUser().getUserId());
        feedBack.setFeedbackDate(LocalDateTime.now());
        //新增插入记录
        boolean b = feedBackService.save(feedBack);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(feedBack);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delFeedBacks")
    public OperationResultBean<String> delFeedBacks(@RequestParam List<String> keys) {
        boolean b = feedBackService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
