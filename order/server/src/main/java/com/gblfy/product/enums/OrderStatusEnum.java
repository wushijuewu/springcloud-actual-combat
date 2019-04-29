package com.gblfy.product.enums;

import lombok.Getter;

/**
 * @ClassName OrderStatus
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:12
 * @Version 1.0
 **/
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCLE(2,"取消"),
        ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
