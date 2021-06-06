package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.dao.CardsMapper;
import com.xingying.shopping.master.entity.Cards;
import com.xingying.shopping.master.entity.GoodsDetails;
import com.xingying.shopping.master.dao.GoodsDetailsMapper;
import com.xingying.shopping.master.entity.ext.GoodsDetailsExt;
import com.xingying.shopping.master.service.GoodsDetailsService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
 * @since 2021-05-27
 */
@Service
public class GoodsDetailsServiceImpl extends ServiceImpl<GoodsDetailsMapper, GoodsDetails> implements GoodsDetailsService {

    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private CardsMapper cardsMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    /**
     * 分页列表查询商品详细表
     * @param pageQueryEntity
     * @return
     */
    @Override
    public PageInfo<GoodsDetailsExt> getListByPage(PageQueryEntity<GoodsDetails> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<GoodsDetailsExt> list = goodsDetailsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 处理卡密（批处理--批量插入）
     * @param goodsDetails
     */
    private void handlingCardSecrets(GoodsDetailsExt goodsDetails){
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        CardsMapper mapper = session.getMapper(CardsMapper.class);
        String cards = goodsDetails.getCards();
        String id = goodsDetails.getGoodsId();
        String spc = goodsDetails.getGoodsSpc();
        cards = cards.replaceAll(" ", "");
        //根据#来区分
        String[] splits = cards.split("#");
        int count = 0;
        LocalDateTime now = LocalDateTime.now();
        try {
            for (String s : splits) {
                if (s == null || "".equals(s)) {
                    //直接跳过
                    continue;
                }
                Cards card = new Cards();
                //可用标志
                card.setCardId(String.valueOf(snowFakeIdGenerator.nextId()));
                card.setCardFlag(1);
                card.setGoodsCards(s);
                card.setGoodsId(id);
                card.setGoodsSpc(spc);
                card.setCardDateCreate(now);
                mapper.insert(card);
                count++;
                //每3000提交一次
                if (count % 3000 == 0) {
                    session.commit();
                }
            }
        }catch (Exception e) {
            session.rollback();
            throw new RuntimeException("存在重复卡密");
        }finally {
            session.commit();
            session.close();
        }
    }

    /**
     * 新增商品详细表
     * @param goodsDetails
     * @return
     */
    @Override
    public boolean addGoodsDetails(GoodsDetailsExt goodsDetails) {
        QueryWrapper queryWrapper = getQueryWrapper(goodsDetails);
        Integer count = goodsDetailsMapper.selectCount(queryWrapper);
        if (goodsDetails.getCards() != null && goodsDetails.getCards() != "") {
            //进入卡密处理方法
            handlingCardSecrets(goodsDetails);
            QueryWrapper queryWrapper2 = getQueryWrapper2(goodsDetails);
            Integer integer = cardsMapper.selectCount(queryWrapper2);
            //设置库存
            goodsDetails.setGoodsNum(Long.valueOf(integer));
        }
        Assert.isTrue(count == 0, "该商品规格已存在");

        int res = goodsDetailsMapper.insert(goodsDetails);
        Assert.isTrue(res > 0, "商品规格增加失败");
        return true;
    }

    /**
     * 修改商品详细表（如果卡密标志改变则会清空库）
     * @param goodsDetails
     * @return
     */
    @Override
    public boolean editGoodsDetails(GoodsDetails goodsDetails) {
        QueryWrapper queryWrapper = getQueryWrapper(goodsDetails);
        Integer count = goodsDetailsMapper.selectCount(queryWrapper);
        //获取之前的记录
        GoodsDetails before = goodsDetailsMapper.selectOne(queryWrapper);
        Assert.isTrue(count > 0, "该商品规格不存在");
        if (goodsDetails.getGoodsAuto() == 1) {
            //自动发货时不进行更新
            goodsDetails.setGoodsNum(null);
        }
        if (goodsDetails.getGoodsAuto() != null && !before.getGoodsAuto().equals(goodsDetails.getGoodsAuto())) {
            if (goodsDetails.getGoodsAuto() == 1) {
                //开启自动发卡
                //清空库存
                goodsDetails.setGoodsNum(Long.valueOf(0));
            } else if (goodsDetails.getGoodsAuto() == 0) {
                //手动发卡，则清空卡密表
                QueryWrapper queryWrapper2 = getQueryWrapper2(goodsDetails);
                cardsMapper.delete(queryWrapper2);
            }
        }
        int update = goodsDetailsMapper.update(goodsDetails, queryWrapper);
        Assert.isTrue(update > 0, "商品规格修改失败");
        return true;
    }

    /**
     * 删除规格（包含卡密表）
     * @param goodsDetails
     * @return
     */
    @Override
    public boolean delGoodsDetail(GoodsDetails goodsDetails) {
        QueryWrapper queryWrapper = getQueryWrapper(goodsDetails);
        int delete = goodsDetailsMapper.delete(queryWrapper);
        Assert.isTrue(delete > 0, "删除商品失败");
        //删除卡密表
        QueryWrapper queryWrapper2 = getQueryWrapper2(goodsDetails);
        cardsMapper.delete(queryWrapper2);
        return true;
    }

    /**
     * 获取通用的QueryWrapper (GoodsDetails)
     * @param goodsDetails
     * @return
     */
    private QueryWrapper getQueryWrapper(GoodsDetails goodsDetails) {
        QueryWrapper<GoodsDetails> queryWrapper = new QueryWrapper<GoodsDetails>()
                .eq("GOODS_ID", goodsDetails.getGoodsId())
                .eq("GOODS_SPC", goodsDetails.getGoodsSpc());
        return queryWrapper;
    }

    /**
     * 获取通用的QueryWrapper2 (Cards)
     * @param goodsDetails
     * @return
     */
    private QueryWrapper getQueryWrapper2(GoodsDetails goodsDetails) {
        QueryWrapper<Cards> queryWrapper = new QueryWrapper<Cards>()
                .eq("GOODS_ID", goodsDetails.getGoodsId())
                .eq("GOODS_SPC", goodsDetails.getGoodsSpc())
                .eq("CARD_FLAG", 1);
        return queryWrapper;
    }

    /**
     * 添加卡密
     * @param goodsDetails
     * @return
     */
    @Override
    public boolean addGoodsCard(GoodsDetailsExt goodsDetails) {
        handlingCardSecrets(goodsDetails);
        QueryWrapper queryWrapper = getQueryWrapper(goodsDetails);
        QueryWrapper queryWrapper2 = getQueryWrapper2(goodsDetails);
        Integer integer = cardsMapper.selectCount(queryWrapper2);
        //更新库存
        goodsDetails.setGoodsNum(Long.valueOf(integer));
        goodsDetailsMapper.update(goodsDetails,queryWrapper);
        return true;
    }
}
