package com.example.bakery.controller.api;

import com.example.bakery.model.ProductModel;
import com.example.bakery.service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ProductApiController {

    private final ProductService _service;

    public ProductApiController(ProductService service) {
        this._service = service;
    }

    @GetMapping
    public List<ProductModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public ProductModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public ProductModel createModel(@Valid @RequestBody ProductModel product) {
        return _service.create(product);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public ProductModel updateModel(@PathVariable Long id, @Valid @RequestBody ProductModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
