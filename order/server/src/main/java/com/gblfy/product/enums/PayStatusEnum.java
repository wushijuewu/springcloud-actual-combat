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
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
        ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
