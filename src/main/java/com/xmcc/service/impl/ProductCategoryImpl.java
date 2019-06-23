package com.xmcc.service.impl;

import com.xmcc.entity.ProductCategory;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    private ProductCategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> getALLCategory() {
        return categoryRepository.findAll();
    }
}