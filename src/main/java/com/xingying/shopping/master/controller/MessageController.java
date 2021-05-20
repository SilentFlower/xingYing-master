package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.entity.ShopUser;
import com.xingying.shopping.master.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.xingying.shopping.master.service.MessageService;
import com.xingying.shopping.master.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 星荧商城个人消息表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@RestController
@RequestMapping("${xingYing.name}/message")
        public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Message>> getListByPage(@RequestBody PageQueryEntity<Message> params) {
        if (params.getData() == null) {
            Message message = new Message();
            message.setUserId(String.valueOf(UserContext.getCurrentUser().getUserId()));
            params.setData(message);
        }else{
            params.getData().setUserId(String.valueOf(UserContext.getCurrentUser().getUserId()));
        }
        PageInfo<Message> page = messageService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城个人消息表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getMessageByParam")
    public QueryResultBean<Message> getMessage(@RequestParam String key) {
        Message message = messageService.getById(key);
        return new QueryResultBean<>(message);
    }

    /**
     * 新增 星荧商城个人消息表
     * @param message Message 对象
     * @return
     */
    @PostMapping("/addMessage")
    public OperationResultBean<Message> addMessage(@RequestBody Message message) {
        if (message.getMsgId() == null || message.getMsgId() == "") {
            //无ID则为新增信息，且置状态为0，和新增时间
            message.setMsgId(String.valueOf(snowFakeIdGenerator.nextId()));
            message.setMsgStatus(0);
            message.setUserId(String.valueOf(UserContext.getCurrentUser().getUserId()));
            message.setMsgDate(LocalDateTime.now());
        }
        boolean b = messageService.saveOrUpdate(message);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(message);
    }


    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delMessages")
    public OperationResultBean<String> delMessages(@RequestParam List<String> keys) {
        boolean b = messageService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 读信息
     * @param list
     * @return
     */
    @PostMapping("/readMsg")
    public OperationResultBean<String> readMsg(@RequestBody List<String> list) {
        //更新信息状态
        for (String s : list) {
            Message message = new Message();
            message.setMsgId(s);
            message.setMsgStatus(1);
            messageService.updateById(message);
        }
        return new OperationResultBean<>("success");
    }

    /**
     * 读信息
     * @return
     */
    @GetMapping("/readAll")
    public OperationResultBean<String> readAll() {
        Message message = new Message();
        message.setMsgStatus(1);
        messageService.update(message,new QueryWrapper<Message>()
                .eq("USER_ID",String.valueOf(UserContext.getCurrentUser().getUserId())));
        return new OperationResultBean<>("success");
    }
}
