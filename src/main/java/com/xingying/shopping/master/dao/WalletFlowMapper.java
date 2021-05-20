package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.WalletFlow;
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
public interface WalletFlowMapper extends BaseMapper<WalletFlow> {

    List<WalletFlow> getListByPage(WalletFlow walletFlow);
}
