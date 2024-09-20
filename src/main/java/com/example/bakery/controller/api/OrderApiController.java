package com.example.bakery.controller.api;

import com.example.bakery.model.OrderModel;
import com.example.bakery.service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/order")
public class OrderApiController {

    private final OrderService _service;

    public OrderApiController(OrderService service) {
        this._service = service;
    }

    @GetMapping
    public List<OrderModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public OrderModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public OrderModel createModel(@Valid @RequestBody OrderModel order) {
        return _service.create(order);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public OrderModel updateModel(@PathVariable Long id, @Valid @RequestBody OrderModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
