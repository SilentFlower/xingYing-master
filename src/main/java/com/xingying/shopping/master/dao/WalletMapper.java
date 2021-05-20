package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Wallet;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Repository
public interface WalletMapper extends BaseMapper<Wallet> {

    List<Wallet> getListByPage(Wallet wallet);
}
