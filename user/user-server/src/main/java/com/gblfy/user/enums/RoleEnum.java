package com.gblfy.user.enums;

import lombok.Getter;

/**
 * @ClassName RoleEnum
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:42
 * @Version 1.0
 **/
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
