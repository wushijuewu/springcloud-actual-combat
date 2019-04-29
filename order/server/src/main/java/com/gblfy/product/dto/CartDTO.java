package com.gblfy.product.dto;

import lombok.Data;

/**
 * @author gblfy
 * @ClassNme CartDTO
 * @Description TODO
 * @Date 2019/4/21 22:44
 * @version1.0
 */
@Data
public class CartDTO {

    /**
     * 商品的id
     */
    private String productId;

    /**
     * 商品的数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
