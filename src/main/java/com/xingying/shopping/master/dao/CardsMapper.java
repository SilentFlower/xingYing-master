package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Cards;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@Repository
public interface CardsMapper extends BaseMapper<Cards> {

    List<Cards> getListByPage(Cards cards);
}
