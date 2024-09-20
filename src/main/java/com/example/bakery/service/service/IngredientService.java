package com.example.bakery.service.service;

import com.example.bakery.model.CategoryModel;
import com.example.bakery.model.IngredientModel;

import java.util.List;

public interface IngredientService {
    public List<IngredientModel> findAll();
    public IngredientModel findById(Long id);
    public IngredientModel create(IngredientModel model);
    public IngredientModel update(IngredientModel model);
    public void delete(Long id);

    public List<IngredientModel> findByCategory(CategoryModel model);
}
