package com.example.bakery.service.Impl;


import com.example.bakery.model.CategoryModel;
import com.example.bakery.model.IngredientModel;
import com.example.bakery.repository.IngredientRepository;
import com.example.bakery.service.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;


    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IngredientModel> findAll() {
        return repository.findAll();
    }

    @Override
    public IngredientModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public IngredientModel create(IngredientModel model) {
        return repository.save(model);
    }

    @Override
    public IngredientModel update(IngredientModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<IngredientModel> findByCategory(CategoryModel category) {
        return repository.findAll().stream()
                .filter(ingredient -> ingredient.getCategory().equals(category))
                .collect(Collectors.toList());
    }


}
