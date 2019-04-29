package com.gblfy.product.service.impl;

import com.gblfy.product.DecreaseStockInput;
import com.gblfy.product.ProductInfoOutput;
import com.gblfy.product.client.ProductClient;
import com.gblfy.product.dataobject.OrderDetail;
import com.gblfy.product.dataobject.OrderMaster;
import com.gblfy.product.dto.OrderDTO;
import com.gblfy.product.enums.OrderStatusEnum;
import com.gblfy.product.enums.PayStatusEnum;
import com.gblfy.product.enums.ResultEnum;
import com.gblfy.product.exception.OrderException;
import com.gblfy.product.repository.OrderDetailRepository;
import com.gblfy.product.repository.OrderMasterRepository;
import com.gblfy.product.service.OrderService;
import com.gblfy.product.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:38
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {
    //1.库存在Redis保存
    //2.收到请求Redis判断库存是否充足，减掉Redis中的库存
    //3.订单服务创建订单写入数据库，并发送消息

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(String orderId) {

        //1.先查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        //4.查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        //5.构造orderDTO对象
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
