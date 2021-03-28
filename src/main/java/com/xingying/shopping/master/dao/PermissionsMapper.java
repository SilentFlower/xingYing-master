package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Permissions;
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
public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<Permissions> getListByPage(Permissions permissions);

    /**
     * 根据角色来获取角色所拥有的权限
     * @param roles
     * @return
     */
    List<Permissions> getPermissionsByRoles(List<Role> roles);
}
