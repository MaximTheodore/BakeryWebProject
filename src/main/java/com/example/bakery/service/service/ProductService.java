package com.example.bakery.service.service;

import com.example.bakery.model.ProductModel;

import java.util.List;

public interface ProductService {
    public List<ProductModel> findAll();
    public ProductModel findById(Long id);
    public ProductModel create(ProductModel model);
    public ProductModel update(ProductModel model);
    public void delete(Long id);
}
