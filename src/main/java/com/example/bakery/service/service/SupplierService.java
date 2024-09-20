package com.example.bakery.service.service;



import com.example.bakery.model.SupplierModel;

import java.util.List;

public interface SupplierService {
    public List<SupplierModel> findAll();
    public SupplierModel findById(Long id);
    public SupplierModel create(SupplierModel model);
    public SupplierModel update(SupplierModel model);
    public void delete(Long id);
}
