package com.example.bakery.service.Impl;

import com.example.bakery.model.SupplierModel;
import com.example.bakery.repository.SupplierRepository;
import com.example.bakery.service.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SupplierModel> findAll() {
        return repository.findAll();
    }

    @Override
    public SupplierModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SupplierModel create(SupplierModel model) {
        return repository.save(model);
    }

    @Override
    public SupplierModel update(SupplierModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
