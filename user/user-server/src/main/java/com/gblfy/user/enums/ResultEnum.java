package com.gblfy.user.enums;

import lombok.Getter;

/**
 * Created by gblfy
 * 2019-05-10 23:00
 */
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(1, "登角色权限有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
