package com.example.bakery.controller.api;

import com.example.bakery.model.StorehouseModel;
import com.example.bakery.service.service.StorehouseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/storehouse")
public class StorehouseApiController {

    private final StorehouseService _service;

    public StorehouseApiController(StorehouseService service) {
        this._service = service;
    }

    @GetMapping
    public List<StorehouseModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public StorehouseModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public StorehouseModel createModel(@Valid @RequestBody StorehouseModel storehouse) {
        return _service.create(storehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public StorehouseModel updateModel(@PathVariable Long id, @Valid @RequestBody StorehouseModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
