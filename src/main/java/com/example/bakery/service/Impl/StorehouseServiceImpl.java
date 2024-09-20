package com.example.bakery.service.Impl;

import com.example.bakery.model.StorehouseModel;
import com.example.bakery.repository.StorehouseRepository;
import com.example.bakery.service.service.StorehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorehouseServiceImpl implements StorehouseService {

    private final StorehouseRepository repository;

    public StorehouseServiceImpl(StorehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StorehouseModel> findAll() {
        return repository.findAll();
    }

    @Override
    public StorehouseModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public StorehouseModel create(StorehouseModel model) {
        return repository.save(model);
    }

    @Override
    public StorehouseModel update(StorehouseModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
