package com.gblfy.product.service;

import com.gblfy.product.dto.OrderDTO;

/**
 * @ClassName OrderService
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:35
 * @Version 1.0
 **/
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单
     *
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
