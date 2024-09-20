package com.example.bakery.controller.api;

import com.example.bakery.model.SupplierModel;
import com.example.bakery.service.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/supplier")
public class SupplierApiController {

    private final SupplierService _service;

    public SupplierApiController(SupplierService service) {
        this._service = service;
    }

    @GetMapping
    public List<SupplierModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public SupplierModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public SupplierModel createModel(@Valid @RequestBody SupplierModel supplier) {
        return _service.create(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public SupplierModel updateModel(@PathVariable Long id, @Valid @RequestBody SupplierModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
