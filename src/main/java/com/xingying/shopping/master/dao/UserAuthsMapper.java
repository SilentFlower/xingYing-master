package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.UserAuths;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-04-16
 */
@Repository
public interface UserAuthsMapper extends BaseMapper<UserAuths> {

    List<UserAuths> getListByPage(UserAuths userAuths);
}
