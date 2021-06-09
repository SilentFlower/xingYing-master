package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.io.ioUtils;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.config.rabbitMq.mqSender;
import com.xingying.shopping.master.dao.GoodsDetailsMapper;
import com.xingying.shopping.master.entity.Goods;
import com.xingying.shopping.master.dao.GoodsMapper;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import com.xingying.shopping.master.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;
    @Autowired
    private com.xingying.shopping.master.config.rabbitMq.mqSender mqSender;
    private static final String PIC_URL = "/app/shop/goods/";

    @Override
    public PageInfo<GoodsExt> getListByPage(PageQueryEntity<GoodsExt> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<GoodsExt> list = goodsMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addGoods(Goods goods) {
        //设置商品ID
        String goodsId = String.valueOf(snowFakeIdGenerator.nextId());
        goods.setGoodsId(goodsId);
        goods.setDataCreateTime(LocalDateTime.now());
        goods.setShopId(UserContext.getCurrentUser().getUserId());
        //表示启用
        goods.setStatus(1);
        //拼接URL
        String url = PIC_URL + goodsId + "/goods.png";
        MultipartFile pic = goods.getPic();
        //设置图片路径
        if(pic != null && !pic.isEmpty()){
            //清空字节
            goods.setPic(null);
            //设置图片的url
            goods.setGoodsPic("http://www.747698.xyz/shop/goods/"+goodsId + "/goods.png");
        }
        boolean b = this.saveOrUpdate(goods);
        if(b && pic != null && !pic.isEmpty()){
            //写入图片
            ioUtils.writerBytes(url, pic);
        }
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editGoods(Goods goods) {
        //设置修改时间
        goods.setDataEditTime(LocalDateTime.now());
        //拼接URL
        String url = PIC_URL + goods.getGoodsId() + "/goods.png";
        MultipartFile pic = goods.getPic();
        //如果修改图片
        if(pic!= null && !pic.isEmpty()){
            //清空字节
            goods.setPic(null);
        }
        boolean b = this.saveOrUpdate(goods);
        if(b && pic != null && !pic.isEmpty()){
            //写入图片
            ioUtils.writerBytes(url, pic);
        }
        return b;
    }

    /**
     * 分页获取商品列表
     * @param param
     * @return
     */
    @Override
    public PageInfo<GoodsExt> getGoodsByPage(PageQueryEntity<Goods> param) {
        PageHelper.startPage(param.getPageNumber(), param.getPageSize());
        List<GoodsExt> list = goodsMapper.getGoodsByPage(param.getData());
        return new PageInfo<GoodsExt>(list);
    }

    /**
     * 删除商品表（包含详细表内容）
     * @param goodsId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delGoodss(String goodsId) {
        //删除主表（假删除,更改status）
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setStatus(0);
        int i = goodsMapper.updateById(goods);
        //删除详细表(真删除)
        goodsDetailsMapper.deleteById(goodsId);
        Assert.isTrue(i > 0, "删除失败");
        //拼接URL
//        String url = PIC_URL + goodsId;
        //删除商品的目录(暂时保留)
//        ioUtils.delete(url);
        return true;
    }
}
