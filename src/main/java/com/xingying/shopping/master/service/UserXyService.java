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

    /**
     * 修改密码
     * @param map
     * @return
     */
    String editPwd(Map<String, Object> map);

    /**
     * 完善密码
     * @param map
     * @return
     */
    String completePwd(Map<String, Object> map);
}
