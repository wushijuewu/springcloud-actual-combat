package com.gblfy.product.vo;

import lombok.Data;

/**
 * @author gblfy
 * @ClassNme ResultVO
 * @Description TODO
 * @Date 2019/4/22 21:36
 * @version1.0
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
