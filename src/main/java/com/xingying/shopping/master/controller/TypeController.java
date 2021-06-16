package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.entity.request.ClassGoodReq;
import com.xingying.shopping.master.entity.response.ClassGood;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import java.time.LocalDateTime;
import java.util.List;

import com.xingying.shopping.master.service.TypeService;
import com.xingying.shopping.master.entity.Type;

/**
 * <p>
 * 星荧虚拟商品交易系统的商品种类表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@RestController
@RequestMapping("${xingYing.name}/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页列表
     *
     * @param params 分页信息
     * @return Result
     */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<Type>> getListByPage(@RequestBody PageQueryEntity<Type> params) {
        PageInfo<Type> page = typeService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧虚拟商品交易系统的商品种类表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getTypeByParam")
    public QueryResultBean<Type> getType(@RequestParam String key) {
        Type type = typeService.getById(key);
        return new QueryResultBean<>(type);
    }

    /**
     * 新增 根类
     *
     * @param type Type 对象
     * @return
     */
    @PostMapping("/addRootType")
    public OperationResultBean<Type> addRootType(Type type) {
        System.out.println("???");
        boolean b = typeService.addRootType(type);
        Assert.isTrue(b, "新增失败");
        return new OperationResultBean<>(type);
    }

    /**
     * 新增 子类
     *
     * @param type Type 对象
     * @return
     */
    @PostMapping("/addChildType")
    public OperationResultBean<Type> addChildType(Type type) {
        boolean b = typeService.addChildType(type);
        Assert.isTrue(b, "新增失败");
        return new OperationResultBean<>(type);
    }

    /**
     * 修改 类别
     *
     * @param type Type 对象
     * @return
     */
    @PostMapping("/editType")
    public OperationResultBean<Type> editType(Type type) {
        boolean b = typeService.editType(type);
        Assert.isTrue(b, "新增失败");
        return new OperationResultBean<>(type);
    }

    /**
     * 新增 星荧虚拟商品交易系统的商品种类表
     *
     * @param type Type 对象
     * @return
     */
    @PostMapping("/addType")
    public OperationResultBean<Type> addType(@RequestBody Type type) {
        boolean b = typeService.saveOrUpdate(type);
        Assert.isTrue(b, "新增失败");
        return new OperationResultBean<>(type);
    }

    /**
     * 删除(假删除)
     *
     * @param typeId key字符串，根据,号分隔
     * @return Result
     */
    @GetMapping("/delType")
    public OperationResultBean<String> delType(String typeId) {
        boolean b = typeService.delType(typeId);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 获取所有根节点
     */
    @GetMapping("/getRoot")
    public OperationResultBean<List<Type>> getRoot() {
        List<Type> list = typeService.list(new QueryWrapper<Type>()
                .eq("ROOT_FLAG", 1)
                .eq("STATUS", 1));
        for (Type type : list) {
            int count = typeService.count(new QueryWrapper<Type>()
                    .eq("PARENT_ID", type.getTypeId())
                    .eq("STATUS", 1));
            if (count > 0) {
                type.setLeafFlag(false);
            }else{
                type.setLeafFlag(true);
            }
        }
        return new OperationResultBean<>(list);
    }

    /**
     * 获取所有子节点
     */
    @GetMapping("/getChild")
    public OperationResultBean<List<Type>> getChild() {
        List<Type> list = typeService.list(new QueryWrapper<Type>()
                .eq("ROOT_FLAG", 0)
                .eq("STATUS", 1));
        return new OperationResultBean<>(list);
    }

    /**
     * {
     * /GET
     *      parentId:
     * }
     * 获取所有子节点
     */
    @GetMapping("/getChildren")
    public OperationResultBean<List<Type>> getChildren(@RequestParam(name = "parentId") String parentId) {
        List<Type> list = typeService.list(new QueryWrapper<Type>()
                .eq("PARENT_ID", parentId)
                .eq("STATUS", 1));
        for (Type type : list) {
            int count = typeService.count(new QueryWrapper<Type>()
                    .eq("PARENT_ID", type.getTypeId())
                    .eq("STATUS", 1));
            if (count > 0) {
                type.setLeafFlag(false);
            }else{
                type.setLeafFlag(true);
            }
        }
        return new OperationResultBean<>(list);
    }

    /**
     * 获取商品
     * @param req
     * @return
     */
    @GetMapping("/getClassGoodForHome")
    public QueryResultBean<ClassGood> getClassGoodForHome(ClassGoodReq req) {
        ClassGood classGood = typeService.getClassGoodForHome(req);
        return new QueryResultBean<>(classGood);
    }
}
