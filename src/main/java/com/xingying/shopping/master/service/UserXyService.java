package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.UserXy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  UserXyService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-03-22
 */
public interface UserXyService extends IService<UserXy> {

    PageInfo<UserXy> getListByPage(PageQueryEntity<UserXy> params);

    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean addUser(UserXy user);

    /**
     * google登陆生成用户信息并返回UID
     * @return
     */
    Long addUserByGoogle(Map<String,String> map);
}
