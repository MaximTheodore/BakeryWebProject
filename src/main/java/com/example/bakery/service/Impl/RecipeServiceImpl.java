package com.example.bakery.service.Impl;

import com.example.bakery.model.RecipeModel;
import com.example.bakery.repository.RecipeRepository;
import com.example.bakery.service.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {


    private final RecipeRepository repository;

    public RecipeServiceImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RecipeModel> findAll() {
        return repository.findAll();
    }

    @Override
    public RecipeModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RecipeModel create(RecipeModel model) {
        return repository.save(model);
    }

    @Override
    public RecipeModel update(RecipeModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
