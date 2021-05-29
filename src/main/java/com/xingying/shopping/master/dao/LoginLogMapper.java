package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.LoginLog;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-23
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    List<LoginLog> getListByPage(LoginLog loginLog);
}
