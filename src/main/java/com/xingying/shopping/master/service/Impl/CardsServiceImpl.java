package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.dao.GoodsDetailsMapper;
import com.xingying.shopping.master.entity.Cards;
import com.xingying.shopping.master.dao.CardsMapper;
import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@Service
public class CardsServiceImpl extends ServiceImpl<CardsMapper, Cards> implements CardsService {

    @Autowired
    private CardsMapper cardsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;

    @Override
    public PageInfo<Cards> getListByPage(PageQueryEntity<Cards> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Cards> list = cardsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 更新库存
     * @param cards
     */
    @Override
    public void updateGoods(Cards cards) {
        String goodsId = cards.getGoodsId();
        String goodsSpc = cards.getGoodsSpc();
        Integer integer = cardsMapper.selectCount(new QueryWrapper<Cards>()
                .eq("GOODS_ID", goodsId)
                .eq("GOODS_SPC", goodsSpc)
                .eq("CARD_FLAG", 1));
        GoodsDetails goodsDetails = new GoodsDetails();
        goodsDetails.setGoodsId(goodsId);
        goodsDetails.setGoodsSpc(goodsSpc);
        goodsDetails.setGoodsNum(Long.valueOf(integer));
        goodsDetailsMapper.update(goodsDetails, new QueryWrapper<GoodsDetails>()
                .eq("GOODS_ID", goodsId)
                .eq("GOODS_SPC", goodsSpc));
    }
}

