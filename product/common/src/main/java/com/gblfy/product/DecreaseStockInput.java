package com.gblfy.product;

import lombok.Data;

/**
 * 减库存入参
 * Created by gblfy
 * 2019-05-20 22:50
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}