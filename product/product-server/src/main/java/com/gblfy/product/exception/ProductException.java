package com.gblfy.product.exception;


import com.gblfy.product.enums.ResultEnum;

/**
 * Created by gblfy
 * 2019-05-10 22:59
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
