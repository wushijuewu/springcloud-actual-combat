package com.gblfy.product.exception;

import com.gblfy.product.enums.ResultEnum;

/**
 * @ClassName OrderException
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:52
 * @Version 1.0
 **/
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
