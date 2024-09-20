package com.example.bakery.service.service;



import com.example.bakery.model.StorehouseModel;

import java.util.List;

public interface StorehouseService {
    public List<StorehouseModel> findAll();
    public StorehouseModel findById(Long id);
    public StorehouseModel create(StorehouseModel model);
    public StorehouseModel update(StorehouseModel model);
    public void delete(Long id);
}
