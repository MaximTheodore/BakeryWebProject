package com.example.bakery.service.Impl;

import com.example.bakery.model.ProductModel;
import com.example.bakery.repository.ProductRepository;
import com.example.bakery.service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public List<ProductModel> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProductModel create(ProductModel model) {
        return repository.save(model);
    }

    @Override
    public ProductModel update(ProductModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
