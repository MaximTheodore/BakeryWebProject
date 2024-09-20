package com.example.bakery.service.Impl;

import com.example.bakery.model.OrderModel;
import com.example.bakery.repository.OrderRepository;
import com.example.bakery.service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderModel> findAll() {
        return repository.findAll();
    }

    @Override
    public OrderModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public OrderModel create(OrderModel model) {
        return repository.save(model);
    }

    @Override
    public OrderModel update(OrderModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
