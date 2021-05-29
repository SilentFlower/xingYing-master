package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.dao.GoodsDetailsMapper;
import com.xingying.shopping.master.entity.GoodsCards;
import com.xingying.shopping.master.dao.GoodsCardsMapper;
import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.service.GoodsCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-28
 */
@Service
public class GoodsCardsServiceImpl extends ServiceImpl<GoodsCardsMapper, GoodsCards> implements GoodsCardsService {

    @Autowired
    private GoodsCardsMapper goodsCardsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;

    @Override
    public PageInfo<GoodsCards> getListByPage(PageQueryEntity<GoodsCards> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<GoodsCards> list = goodsCardsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 删除
     * @param goodsCard
     * @return
     */
    @Override
    public boolean delGoodsCard(GoodsCards goodsCard) {
        int delete = goodsCardsMapper.delete(new QueryWrapper<GoodsCards>()
                .eq("GOODS_ID", goodsCard.getGoodsId())
                .eq("GOODS_SPC", goodsCard.getGoodsSpc())
                .eq("GOODS_CARDS", goodsCard.getGoodsCards()));
        Assert.isTrue(delete > 0, "删除卡密失败");
        return true;
    }

    /**
     * 更新库存
     * @param goodsCards
     */
    @Override
    public void updateGoods(GoodsCards goodsCards) {
        String goodsId = goodsCards.getGoodsId();
        String goodsSpc = goodsCards.getGoodsSpc();
        Integer integer = goodsCardsMapper.selectCount(new QueryWrapper<GoodsCards>()
                .eq("GOODS_ID", goodsId)
                .eq("GOODS_SPC", goodsSpc)
                .eq("CARDS_FLAG", 1));
        GoodsDetails goodsDetails = new GoodsDetails();
        goodsDetails.setGoodsId(goodsId);
        goodsDetails.setGoodsSpc(goodsSpc);
        goodsDetails.setGoodsNum(Long.valueOf(integer));
        goodsDetailsMapper.update(goodsDetails, new QueryWrapper<GoodsDetails>()
                .eq("GOODS_ID", goodsId)
                .eq("GOODS_SPC", goodsSpc));
    }
}
