package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.config.rabbitMq.mqSender;
import com.xingying.shopping.master.dao.*;
import com.xingying.shopping.master.entity.*;
import com.xingying.shopping.master.entity.ext.GoodsAndOrderDetail;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.rabbit.OrderRabbit;
import com.xingying.shopping.master.entity.request.MakeOrderRes;
import com.xingying.shopping.master.entity.request.OrderGoodsRes;
import com.xingying.shopping.master.entity.request.PayOrder;
import com.xingying.shopping.master.entity.response.AppealNum;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;
import com.xingying.shopping.master.service.CardsService;
import com.xingying.shopping.master.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderMaster> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private CouponFlowMapper couponFlowMapper;
    @Autowired
    private CouponUserMapper couponUserMapper;
    @Autowired
    private ShopcartMapper shopcartMapper;
    @Autowired
    private WalletFlowMapper walletFlowMapper;
    @Autowired
    private CardsService cardsService;
    @Autowired
    private mqSender sender;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    @Override
    public PageInfo<OrderMaster> getListByPage(PageQueryEntity<OrderMaster> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<OrderMaster> list = orderMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 分页查询 订单（时间倒序）
     * @param params
     * @return
     */
    @Override
    public PageInfo<OrderMasterExt> getOrdersByIdAndTime(PageQueryEntity<OrderMasterExt> params) {
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        List<OrderMasterExt> list = orderMapper.getOrdersByIdAndTime(params.getData());
        return new PageInfo<>(list);
    }

    /**
     * 查询今日支出、收入、今日订单、总成交订单
     * @return
     */
    @Override
    public OrderStatistics getOrdersByIdForSum() {
        return orderMapper.getOrdersByIdForSum(UserContext.getCurrentUser().getUserId());
    }

    /**
     * 订单支付
     * @param payOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean payOrder(PayOrder payOrder) {
        String userId = UserContext.getCurrentUser().getUserId();
        LocalDateTime now = LocalDateTime.now();
        //1.寻找相关订单
        OrderMaster orderMaster = orderMapper.selectById(payOrder.getOrderId());
        //2.根据支付方式不同选择相应的支付
        if (payOrder.getPayType() == 0) {
            //钱包支付。首先查看钱包余额
            Wallet wallet = walletMapper.selectById(userId);
            BigDecimal balance = wallet.getBalance();
            //比较余额与订单余额
            int i = balance.compareTo(orderMaster.getPayAmount());
            Assert.isTrue(i > -1, "钱包余额不足");
            //正常则扣除钱包余额，并且生成相应的消费记录
            //扣除钱包（减少）
            walletMapper.updateBalance(orderMaster.getPayAmount(), userId, 0);
            //生成钱包记录表
            WalletFlow walletFlow = new WalletFlow();
            walletFlow.setUserId(userId);
            walletFlow.setWalletFlowBalance(balance.subtract(orderMaster.getPayAmount()));
            walletFlow.setWalletFlowStatus(1);
            walletFlow.setWalletFlowId(String.valueOf(snowFakeIdGenerator.nextId()));
            //涉及金额
            walletFlow.setWalletFlowFee(orderMaster.getPayAmount());
            //3为下单
            walletFlow.setWalletFlowType(3);
            walletFlow.setWalletFlowDate(now);
            walletFlowMapper.insert(walletFlow);
        }else{
            //其他支付。暂定支付宝接口
            //生成钱包记录表
            WalletFlow walletFlow = new WalletFlow();
            walletFlow.setUserId(userId);
            walletFlow.setWalletFlowBalance(BigDecimal.valueOf(0));
            walletFlow.setWalletFlowStatus(1);
            walletFlow.setWalletFlowId(String.valueOf(snowFakeIdGenerator.nextId()));
            //涉及金额
            walletFlow.setWalletFlowFee(orderMaster.getPayAmount());
            //3为下单
            walletFlow.setWalletFlowType(3);
            walletFlow.setWalletFlowDate(now);
            walletFlowMapper.insert(walletFlow);
            //4更新用户的钱包总支出
            walletMapper.updateBalance2(orderMaster.getPayAmount(), userId);
        }
        //2.5 更新商家的钱包（增加）
        String shopId = orderMaster.getShopId();
        walletMapper.updateBalance(orderMaster.getPayAmount(), shopId, 1);
        //3.修改orderMaster的状态（待发货状态 2）
        orderMaster.setStatus(2);
        orderMaster.setDataEditTime(now);
        orderMapper.updateById(orderMaster);
        //4.查看子订单中是否有自动发货项，有则自动发货
        List<GoodsAndOrderDetail> auto = orderDetailMapper.findAutoSend(payOrder.getOrderId());
        if (auto != null) {
            for (GoodsAndOrderDetail goodsAndOrderDetail : auto) {
                OrderDetail orderDetail = goodsAndOrderDetail.getOrderDetail();
                //进行卡密发货
                cardsService.cardSend(goodsAndOrderDetail);
                //更新子订单状态(发货完成)
                orderDetail.setStatus(2);
                orderDetailMapper.updateById(orderDetail);
            }
        }
        //5.如果大订单内全为自动发货，那主订单会更新为已发货状态
        //先查询子订单中的个数(如果和4过程中的数量相同，则自动更新为主表中的订单状态)
        Integer integer = orderDetailMapper.selectCount(new QueryWrapper<OrderDetail>()
                .eq("ORDER_ID", payOrder.getOrderId()));
        if (integer == auto.size()) {
            // 6为待发货
            orderMaster.setStatus(6);
            orderMapper.updateById(orderMaster);
        }
        return true;
    }


    /**
     * 订单确认
     * @param payOrder
     * @return
     */
    @Override
    public boolean confirmOrder(PayOrder payOrder) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setStatus(5);
        orderMaster.setDataComTime(LocalDateTime.now());
        orderMaster.setOrderId(payOrder.getOrderId());
        int i = orderMapper.updateById(orderMaster);
        Assert.isTrue(i > 0, "订单确认失败");
        return true;
    }

    /**
     * 订单申诉
     * @param payOrder
     * @return
     */
    @Override
    public boolean appealOrder(PayOrder payOrder) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setStatus(4);
        orderMaster.setDataEditTime(LocalDateTime.now());
        orderMaster.setAppealFlag(1);
        orderMaster.setOrderId(payOrder.getOrderId());
        int i = orderMapper.updateById(orderMaster);
        Assert.isTrue(i > 0, "订单申诉失败");
        return true;
    }

    /**
     * 查询x日内的统计数据
     * @return
     */
    @Override
    public List<OrderStatisticsForPic> getStatisticsForPic() {
        List<OrderStatisticsForPic> res = new ArrayList<OrderStatisticsForPic>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 5; i >= 0; i--) {
            //查询的时间
            LocalDateTime orderTime  = now.minusDays(i);
            OrderStatisticsForPic one = orderMapper.getStatisticsForPic(orderTime,UserContext.getCurrentUser().getUserId());
            one.setOrderTime(orderTime);
            res.add(one);
        }
        return res;
    }

    /**
     * 生成订单(并放入到rabbitmq队列中，10分钟不支付自动取消)
     * @param makeOrderRes
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> makeOrder(MakeOrderRes makeOrderRes) {
        Map<String, List<Coupon>> couponMap = makeOrderRes.getCouponMap();
        Map<String, List<OrderGoodsRes>> goodsMap = makeOrderRes.getGoodsMap();
        List<String> ids = createOrder(couponMap, goodsMap);
        return ids;
    }

    /**
     * 金额退回
     * @param payOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean backAmount(PayOrder payOrder) {
        OrderMaster orderMaster = orderMapper.selectById(payOrder.getOrderId());
        BigDecimal backAmount = payOrder.getBackAmount();
        int i = orderMaster.getPayAmount().compareTo(backAmount);
        //如果支付的金额大于退回金额则正常进行
        if (i > -1) {
            //更新余额
            //顾客的钱增加
            walletMapper.updateBalance(backAmount, orderMaster.getUserId(), 1);
            //商家的钱减少(可能出现钱包余额少于0的情况时候直接报错)
            Boolean b = walletMapper.updateBalance(backAmount, orderMaster.getShopId(), 0);
            Assert.isTrue(b, "商家钱包余额不足，请充值");
            orderMaster.setPayAmount(orderMaster.getPayAmount().subtract(backAmount));
            BigDecimal before = orderMaster.getBackAmount();
            if (before != null) {
                backAmount = backAmount.add(before);
            }
            orderMaster.setBackAmount(backAmount);
            int i1 = orderMapper.updateById(orderMaster);
            Assert.isTrue(i1 > 0, "退款失败");
            Message message = new Message();
            message.setMsgStatus(0);
            message.setMsgDate(LocalDateTime.now());
            message.setSendId(UserContext.getCurrentUser().getUserId());
            message.setMsgId(String.valueOf(snowFakeIdGenerator.nextId()));
            message.setUserId(payOrder.getUserId());
            message.setMsgType("退款信息");
            message.setMsgContent("申诉订单:" + payOrder.getOrderId() + "已经退款￥" + payOrder.getBackAmount());
            messageMapper.insert(message);
        }
        Assert.isTrue(i > -1, "授予退款金额大于付款金额");
        return true;
    }

    /**
     * 申诉结束
     * @param payOrder
     * @return
     */
    @Override
    public boolean appealOrderDone(PayOrder payOrder) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setStatus(5);
        orderMaster.setDataEditTime(LocalDateTime.now());
        orderMaster.setOrderId(payOrder.getOrderId());
        int i = orderMapper.updateById(orderMaster);
        Assert.isTrue(i > 0, "申诉结束失败");
        Message message = new Message();
        message.setMsgStatus(0);
        message.setMsgDate(LocalDateTime.now());
        message.setSendId(UserContext.getCurrentUser().getUserId());
        message.setMsgId(String.valueOf(snowFakeIdGenerator.nextId()));
        message.setUserId(payOrder.getUserId());
        message.setMsgType("订单状态提醒");
        message.setMsgContent("申诉订单:" + payOrder.getOrderId() + "已经结束申诉");
        messageMapper.insert(message);
        return true;
    }

    /**
     * 申诉订单数量查询
     * @return
     */
    @Override
    public AppealNum getAppealNum() {
        String userId = UserContext.getCurrentUser().getUserId();
        Wallet wallet = walletMapper.selectById(userId);
        AppealNum appealNum = orderMapper.getAppealNum(userId);
        appealNum.setFrozenAmount(wallet.getBalanceDisable());
        return appealNum;
    }

    /**
     * 生成订单
     * @param couponMap
     * @param goodsMap
     * @return
     */
    private List<String> createOrder(Map<String, List<Coupon>> couponMap, Map<String, List<OrderGoodsRes>> goodsMap){
        String userId = UserContext.getCurrentUser().getUserId();
        List<String> ids = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String s : goodsMap.keySet()) {
            //获取相关的优惠券
            List<OrderGoodsRes> goodsList = goodsMap.get(s);
            //获取相关的优惠券
            List<Coupon> couponList = new ArrayList<>();
            if (couponMap != null) {
                couponList = couponMap.get(s);
            }
            List<CouponFlow> couponFlows = new ArrayList<CouponFlow>();
            BigDecimal all = BigDecimal.ZERO;
            //先计算合计价格
            for (OrderGoodsRes goods : goodsList) {
                 //清空相关购物车
                shopcartMapper.delete(new QueryWrapper<Shopcart>()
                        .eq("USER_ID", userId)
                        .eq("GOODS_ID", goods.getGoodsId())
                        .eq("GOODS_SPC", goods.getGoodsSpc()));
                //修正价格
                BigDecimal rightPrice = goodsDetailsMapper.getRightPrice(goods);
                goods.setGoodsPrice(rightPrice);
                //再计算合计
                all = all.add(goods.getGoodsPrice().multiply(BigDecimal.valueOf(goods.getGoodsNum())));
            }
            System.out.println(all);
            if(couponList != null){
                for (Coupon coupon : couponList) {
                    //为商品优惠券时
                    if (coupon.getGoodsId() != null) {
                        for (OrderGoodsRes goods : goodsList) {
                            if (goods.getGoodsId().equals(coupon.getGoodsId())) {
                                if (goods.getGoodsPrice() == null) {
                                    //设置计算出的价格
                                    goods.setGoodsPrice(goods.getGoodsPrice().multiply(BigDecimal.valueOf(goods.getGoodsNum()))
                                            .setScale(2, RoundingMode.HALF_UP)
                                    );
                                }
                                if (coupon.getCouponLimit() == null || goods.getGoodsPrice().compareTo(coupon.getCouponLimit()) > -1) {
                                    BigDecimal disCountPrice = new BigDecimal(0);
                                    if (coupon.getCouponUseType().equals("折扣优惠券")) {
                                        disCountPrice = goods.getGoodsPrice().multiply(coupon.getCouponValue().multiply(BigDecimal.valueOf(0.1)));
                                    }else if(coupon.getCouponUseType().equals("满减优惠券")){
                                        disCountPrice = goods.getGoodsPrice().subtract(coupon.getCouponValue());
                                    }
                                    all = all.subtract(goods.getGoodsPrice()).add(disCountPrice);
                                    goods.setAmount(disCountPrice.setScale(2, RoundingMode.HALF_UP));
                                    //将优惠券使用记录存入
                                    CouponFlow couponFlow = new CouponFlow();
                                    //启用标志
                                    couponFlow.setUseDate(now);
                                    couponFlow.setCouponId(coupon.getCouponId());
                                    couponFlow.setGoodsId(goods.getGoodsId());
                                    couponFlows.add(couponFlow);
                                    break;
                                }
                            }
                        }

                    }else{
                        //为店铺优惠券时
                        //计算合计
                        if (coupon.getCouponLimit() == null || all.compareTo(coupon.getCouponLimit()) > -1) {
                            BigDecimal disCountPrice = BigDecimal.ZERO;
                            if (coupon.getCouponUseType().equals("折扣优惠券")) {
                                disCountPrice = all.multiply(coupon.getCouponValue().multiply(BigDecimal.valueOf(0.1)));
                                System.out.println("-----------" + disCountPrice);
                            }else if(coupon.getCouponUseType().equals("满减优惠券")){
                                disCountPrice = all.subtract(coupon.getCouponValue());
                            }
                            all = disCountPrice;
                            System.out.println(all);
                            //将优惠券使用记录存入
                            CouponFlow couponFlow = new CouponFlow();
                            couponFlow.setUseDate(now);
                            couponFlow.setCouponId(coupon.getCouponId());
                            couponFlows.add(couponFlow);
                        }
                    }
                }
            }
            //生成主订单
            String id = createMaster(all, now, s);
            Assert.isTrue(id != null, "新增订单失败");
            ids.add(id);
            //随后创建子订单
            Map<String, String> details = createDetail(goodsList, id);
            //更新优惠券使用记录表
            for (CouponFlow couponFlow : couponFlows) {
                //设置订单主表ID
                couponFlow.setOrderId(id);
                couponFlow.setStatus(1);
                String detailId = details.get(couponFlow.getGoodsId());
                //设置子表ID（如果有）
                couponFlow.setOrderDetailId(detailId);
                couponFlowMapper.insert(couponFlow);
                //并将优惠券标记为使用
                CouponUser couponUser = new CouponUser();
                couponUser.setStatus(0);
                QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<CouponUser>()
                        .eq("USER_ID", userId)
                        .eq("COUPON_ID", couponFlow.getCouponId());
                couponUserMapper.update(couponUser, queryWrapper);
            }
            //设置rabbit所需要的entity
            OrderRabbit orderRabbit = new OrderRabbit();
            orderRabbit.setOrderMasterIds(ids);
            orderRabbit.setUserId(userId);
            //10分钟后判断是否付款，没付款则取消订单和退优惠券
            sender.sendMessageForOrder(orderRabbit, 1000 *60*10);
        }
        return ids;
    }

    /**
     * 创建子订单
     * @param goodsList
     * @param orderId
     */
    private Map<String, String> createDetail(List<OrderGoodsRes> goodsList,String orderId) {
        Map<String, String> ids = new HashMap<String, String>();
        for (OrderGoodsRes goods : goodsList) {
            OrderDetail orderDetail = new OrderDetail();
            String detailId = String.valueOf(snowFakeIdGenerator.nextId());
            orderDetail.setGoodsId(goods.getGoodsId());
            orderDetail.setGoodsSpc(goods.getGoodsSpc());
            orderDetail.setOrderDetailId(detailId);
            orderDetail.setPrice(goods.getGoodsPrice());
            orderDetail.setTotal(goods.getGoodsNum());
            orderDetail.setTotalAmount(goods.getGoodsPrice().multiply(BigDecimal.valueOf(goods.getGoodsNum()))
                    .setScale(2, RoundingMode.HALF_UP));
            //设置为启用
            orderDetail.setStatus(1);
            orderDetail.setOrderId(orderId);
            //先尝试扣减库存
            Boolean b = orderDetailMapper.minStock(goods);
            Assert.isTrue(b, "减库存失败");
            orderDetailMapper.insert(orderDetail);
            ids.put(goods.getGoodsId(),detailId);
        }
        return ids;
    }

    /**
     * 主订单
     * @param allPrice
     * @param now
     * @param shopId
     * @return
     */
    private String createMaster(BigDecimal allPrice,LocalDateTime now,String shopId){
        String id = String.valueOf(snowFakeIdGenerator.nextId());
        //生成主订单
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setDataCreateTime(now);
        orderMaster.setPayAmount(BigDecimal.valueOf(0));
        orderMaster.setOrderId(id);
        orderMaster.setPayAmount(allPrice);
        //代表创建
        orderMaster.setStatus(1);
        orderMaster.setShopId(shopId);
        orderMaster.setUserId(UserContext.getCurrentUser().getUserId());
        int insert = orderMapper.insert(orderMaster);
        if (insert > 0) {
            return id;
        }
        return null;
    }

    /**
     * rabbitmq的处理方法
     * @param orderRabbit
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mqHandler(OrderRabbit orderRabbit) {
        String userId = orderRabbit.getUserId();
        List<String> orderMasterIds = orderRabbit.getOrderMasterIds();
        for (String orderMasterId : orderMasterIds) {
            OrderMaster orderMaster = orderMapper.selectById(orderMasterId);
            //如果10分钟后状态仍是1，即创建状态，则订单自动取消
            if (orderMaster.getStatus() == 1) {
                orderMaster.setStatus(0);
                orderMaster.setDataCalTime(LocalDateTime.now());
                //1.更新主表
                orderMapper.updateById(orderMaster);
                //2.更新子表
                OrderDetail orderDetail = new OrderDetail();
                //直接设置状态为0
                orderDetail.setStatus(0);
                orderDetailMapper.update(orderDetail,new QueryWrapper<OrderDetail>()
                        .eq("ORDER_ID", orderMasterId));
                //寻找优惠券相关记录
                List<CouponFlow> couponFlows = couponFlowMapper.selectList(new QueryWrapper<CouponFlow>()
                        .eq("ORDER_ID", orderMasterId));
                for (CouponFlow couponFlow : couponFlows) {
                    CouponUser couponUser = new CouponUser();
                    couponUser.setStatus(1);
                    QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<CouponUser>()
                            .eq("USER_ID", userId)
                            .eq("COUPON_ID", couponFlow.getCouponId());
                    //3.恢复个人优惠券
                    couponUserMapper.update(couponUser, queryWrapper);
                }
                CouponFlow couponFlow = new CouponFlow();
                //更新记录表状态为0不启用
                couponFlow.setStatus(0);
                //4.删除优惠券使用记录
                couponFlowMapper.update(couponFlow,new QueryWrapper<CouponFlow>()
                        .eq("ORDER_ID", orderMasterId));
                //5.库存回滚
                goodsDetailsMapper.rollBackByMastetId(orderMasterId);
            }
        }

    }

}
