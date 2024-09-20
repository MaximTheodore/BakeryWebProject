package com.example.bakery.service.service;

import com.example.bakery.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    public List<CategoryModel> findAll();
    public CategoryModel findById(Long id);
    public CategoryModel create(CategoryModel model);
    public CategoryModel update(CategoryModel model);
    public void delete(Long id);
}
