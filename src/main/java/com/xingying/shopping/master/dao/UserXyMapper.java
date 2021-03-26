package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.UserXy;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-03-22
 */
@Repository
public interface UserXyMapper extends BaseMapper<UserXy> {

    List<UserXy> getListByPage(UserXy userXy);
}
