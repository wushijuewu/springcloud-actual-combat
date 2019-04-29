package com.gblfy.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName OrderDetail
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 17:57
 * @Version 1.0
 **/
@Data
@Entity
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id*/
    private String orderId;

    /** 商品id*/
    private String productId;

    /** 商品名称*/
    private String productName;

    /** 商品单价*/
    private BigDecimal productPrice;

    /** 商品数量*/
    private Integer productQuantity;

    /** 商品小图*/
    private String productIcon;
}
