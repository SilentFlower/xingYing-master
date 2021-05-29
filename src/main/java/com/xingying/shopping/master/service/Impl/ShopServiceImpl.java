package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.io.ioUtils;
import com.xingying.shopping.master.entity.Shop;
import com.xingying.shopping.master.dao.ShopMapper;
import com.xingying.shopping.master.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-26
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    private static final String PIC_URL = "/app/shop/";

    @Override
    public PageInfo<Shop> getListByPage(PageQueryEntity<Shop> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Shop> list = shopMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 完善或修改 星荧商城商家表
     * @param shop
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editShop(Shop shop) {
        String id = UserContext.getCurrentUser().getUserId();
        shop.setShopId(id);
        //拼接URL
        String url = PIC_URL + id + "/shop.png";
        MultipartFile pic = shop.getShopPic();
        //商家图片内容
        if(pic != null && !pic.isEmpty()){
            //清空字节
            shop.setShopPic(null);
            //设置图片的url
            shop.setShopPicUrl("http://www.747698.xyz/shop/"+id + "/shop.png");
        }
        boolean b = this.saveOrUpdate(shop);
        if (b && pic != null && !pic.isEmpty()) {
            //更新成功且PIC不为空时,则进行io操作
            ioUtils.writerBytes(url, pic);
        }
        return b;
    }
}
