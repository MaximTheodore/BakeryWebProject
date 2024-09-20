package com.example.bakery.service.service;



import com.example.bakery.model.RecipeModel;

import java.util.List;

public interface RecipeService {
    public List<RecipeModel> findAll();
    public RecipeModel findById(Long id);
    public RecipeModel create(RecipeModel model);
    public RecipeModel update(RecipeModel model);
    public void delete(Long id);
}
