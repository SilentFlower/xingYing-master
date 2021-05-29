package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Type;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-27
 */
@Repository
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> getListByPage(Type type);
}
