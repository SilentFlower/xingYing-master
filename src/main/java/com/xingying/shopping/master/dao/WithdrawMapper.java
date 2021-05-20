package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Withdraw;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-21
 */
@Repository
public interface WithdrawMapper extends BaseMapper<Withdraw> {

    List<Withdraw> getListByPage(Withdraw withdraw);
}
