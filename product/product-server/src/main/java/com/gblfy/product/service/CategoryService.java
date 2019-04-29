package com.gblfy.product.service;


import com.gblfy.product.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * Created by gblfy
 * 2019-05-09 22:06
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
