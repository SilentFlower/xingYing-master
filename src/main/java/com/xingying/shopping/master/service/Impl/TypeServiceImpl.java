package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.io.ioUtils;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.dao.GoodsMapper;
import com.xingying.shopping.master.entity.Type;
import com.xingying.shopping.master.dao.TypeMapper;
import com.xingying.shopping.master.entity.ext.GoodsExt;
import com.xingying.shopping.master.entity.request.ClassGoodReq;
import com.xingying.shopping.master.entity.response.ClassGood;
import com.xingying.shopping.master.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;
    @Autowired
    private GoodsMapper goodsMapper;

    private static final String TYPE_URL = "/app/shop/types/";

    @Override
    public PageInfo<Type> getListByPage(PageQueryEntity<Type> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<Type> list = typeMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 新增根类
     * @param type
     * @return
     */
    @Override
    public boolean addRootType(Type type) {
        makeInit(type);
        type.setRootFlag(1);
        MultipartFile pic = type.getPic();
        makePng(type);
        boolean b = this.saveOrUpdate(type);
        if(b){
            makeWrite(type,pic);
        }
        return b;
    }

    /**
     * 新增子类
     * @param type
     * @return
     */
    @Override
    public boolean addChildType(Type type) {
        type.setParentId(type.getTypeId());
        makeInit(type);
        type.setRootFlag(0);
        MultipartFile pic = type.getPic();
        makePng(type);
        boolean b = this.saveOrUpdate(type);
        if(b){
            makeWrite(type,pic);
        }
        return b;
    }

    /**
     * 假删除
     * @param typeId
     * @return
     */
    @Override
    public boolean delType(String typeId) {
        Type type = new Type();
        type.setTypeId(typeId);
        type.setStatus(0);
        boolean b = this.updateById(type);
        return b;
    }

    /**
     * 修改了类别
     * @param type
     * @return
     */
    @Override
    public boolean editType(Type type) {
        type.setDataEditTime(LocalDateTime.now());
        MultipartFile pic = type.getPic();
        makePng(type);
        boolean b = this.saveOrUpdate(type);
        if(b){
            makeWrite(type,pic);
        }
        return b;
    }


    /**
     * 根据类名获取首页所需要展示的商品
     * @param req
     * @return
     */
    @Override
    public ClassGood getClassGoodForHome(ClassGoodReq req) {
        ClassGood classGood = new ClassGood();
        Type type = null;
        //用来根据typeName来获取根类的ID值
        if (req.getTypeName() != null && req.getTypeName() != "") {
            QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>()
                    .eq("TYPE_NAME", req.getTypeName())
                    .eq("STATUS", 1);
            //获取大类的信息
            type = typeMapper.selectOne(queryWrapper);
            classGood.setTypePic(type.getTypePic());
            req.setTypeParentId(type.getTypeId());
        }
        //根据Type的信息获取相应的Goods
        List<GoodsExt> list = goodsMapper.getClassGoodsForHome(req);
        classGood.setGoods(list);
        return classGood;
    }

    /**
     * 初始化
     * @param type
     */
    private void makeInit(Type type){
        String id = String.valueOf(snowFakeIdGenerator.nextId());
        type.setTypeId(id);
        type.setStatus(1);
        type.setDataCreateTime(LocalDateTime.now());
    }
    /**
     * 图片处理
     * @param type
     */
    private void makePng(Type type){
        String url = TYPE_URL + type.getTypeId() + "/type.png";
        MultipartFile pic = type.getPic();
        if(pic != null && !pic.isEmpty()){
            //清空字节
            type.setPic(null);
            //设置图片的url
            type.setTypePic("http://www.747698.xyz/shop/types/"+type.getTypeId() + "/type.png");
        }
    }
    /**
     * 写入处理
     * @param type
     */
    private void makeWrite(Type type,MultipartFile pic){
        String url = TYPE_URL + type.getTypeId() + "/type.png";
        if (pic != null && !pic.isEmpty()) {
            ioUtils.writerBytes(url, pic);
        }
    }
}
