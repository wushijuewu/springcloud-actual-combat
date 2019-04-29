package com.gblfy.product.service.impl;

import com.gblfy.product.dataobject.ProductCategory;
import com.gblfy.product.repository.ProductCategoryRepository;
import com.gblfy.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gblfy
 * 2019-05-09 22:06
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
