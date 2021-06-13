package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.dao.CardsUserMapper;
import com.xingying.shopping.master.dao.GoodsDetailsMapper;
import com.xingying.shopping.master.dao.MessageMapper;
import com.xingying.shopping.master.entity.*;
import com.xingying.shopping.master.dao.CardsMapper;
import com.xingying.shopping.master.entity.ext.GoodsAndOrderDetail;
import com.xingying.shopping.master.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private CardsUserMapper cardsUserMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

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

    /**
     * 卡密发货
     * @return
     */
    @Override
    public boolean cardSend(GoodsAndOrderDetail goodsAndOrderDetail) {
        GoodsDetails goodsDetails = goodsAndOrderDetail.getGoodsDetails();
        OrderDetail orderDetail = goodsAndOrderDetail.getOrderDetail();
        LocalDateTime now = LocalDateTime.now();
        String userId = UserContext.getCurrentUser().getUserId();
        //1.按ID取一个卡密
        Cards cards = cardsMapper.selectOne(new QueryWrapper<Cards>()
                .eq("GOODS_ID", goodsDetails.getGoodsId())
                .eq("GOODS_SPC", goodsDetails.getGoodsSpc())
                .eq("CARD_FLAG", 1)
                .apply("rownum <= {0}", 1)
                .orderByDesc("CARD_ID"));
        Assert.isTrue(cards != null, "卡密获取失败");
        //2.修改此卡密的状态
        cards.setCardFlag(0);
        cardsMapper.updateById(cards);
        //3.将此表放入用户的卡密表中
        CardsUser cardsUser = new CardsUser();
        cardsUser.setCardId(cards.getCardId());
        cardsUser.setGetDate(now);
        cardsUser.setUserId(userId);
        cardsUser.setOrderDetailId(orderDetail.getOrderDetailId());
        cardsUserMapper.insert(cardsUser);
        //4.发送消息
        Message message = new Message(
                String.valueOf(snowFakeIdGenerator.nextId()),
                "卡密发货",
                "卡密发货。您的商品:"+goodsAndOrderDetail.getGoodsName()+"-规格:"+goodsDetails.getGoodsSpc()
                        +"卡密为:"+cards.getGoodsCards(),
                now,
                userId,
                0
        );
        messageMapper.insert(message);
        return true;
    }
}

