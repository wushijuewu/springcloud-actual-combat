package com.gblfy.product.dto;

import com.gblfy.product.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderDTO
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:36
 * @Version 1.0
 **/
@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;

    List<OrderDetail> orderDetailList;
}
