package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.request.ClassGoodReq;
import com.xingying.shopping.master.entity.response.ClassGood;

/**
 * <p>
 *  TypeService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
public interface TypeService extends IService<Type> {

    PageInfo<Type> getListByPage(PageQueryEntity<Type> params);

    /**
     * 新增根类
     * @param type
     * @return
     */
    boolean addRootType(Type type);

    /**
     * 新增子类
     * @param type
     * @return
     */
    boolean addChildType(Type type);

    /**
     * 假删除
     * @param typeId
     * @return
     */
    boolean delType(String typeId);

    /**
     * 修改类别
     * @param type
     * @return
     */
    boolean editType(Type type);


    /**
     * 根据类名获取首页所需要展示的商品
     * @param req
     * @return
     */
    ClassGood getClassGoodForHome(ClassGoodReq req);
}
