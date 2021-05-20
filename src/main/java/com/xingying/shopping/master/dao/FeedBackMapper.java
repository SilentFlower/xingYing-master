package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.FeedBack;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-18
 */
@Repository
public interface FeedBackMapper extends BaseMapper<FeedBack> {

    List<FeedBack> getListByPage(FeedBack feedBack);
}
