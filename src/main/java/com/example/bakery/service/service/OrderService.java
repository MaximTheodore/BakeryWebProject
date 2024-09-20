package com.example.bakery.service.service;



import com.example.bakery.model.OrderModel;

import java.util.List;

public interface OrderService {
    public List<OrderModel> findAll();
    public OrderModel findById(Long id);
    public OrderModel create(OrderModel model);
    public OrderModel update(OrderModel model);
    public void delete(Long id);
}
