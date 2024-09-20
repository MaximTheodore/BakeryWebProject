package com.example.bakery.service.Impl;

import com.example.bakery.model.CategoryModel;
import com.example.bakery.repository.CategoryRepository;
import com.example.bakery.service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CategoryModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CategoryModel create(CategoryModel model) {
        return repository.save(model);
    }

    @Override
    public CategoryModel update(CategoryModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
