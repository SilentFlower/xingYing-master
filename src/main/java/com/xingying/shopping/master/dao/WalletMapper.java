package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.Wallet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
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

    /**
     *更新钱包余额
     * @param payAmount 扣除金额
     * @param userId 用户ID
     * @param type 种类（0为出 1为进）
     * @return
     */
    Boolean updateBalance(@Param("payAmount") BigDecimal payAmount,@Param("userId") String userId,@Param("type")Integer type);

}
