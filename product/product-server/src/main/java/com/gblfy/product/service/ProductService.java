package com.gblfy.product.service;


import com.gblfy.product.DecreaseStockInput;
import com.gblfy.product.ProductInfoOutput;
import com.gblfy.product.dataobject.ProductInfo;

import java.util.List;

/**
 * Created by gblfy
 * 2019-05-09 21:57
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
