package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Role;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-03-21
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getListByPage(Role role);

    /**
     * 通过userID获取该用户所拥有的角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(String userId);
}
