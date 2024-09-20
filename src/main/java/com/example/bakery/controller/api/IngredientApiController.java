package com.example.bakery.controller.api;

import com.example.bakery.model.IngredientModel;
import com.example.bakery.service.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/ingredient")
public class IngredientApiController {

    private final IngredientService _service;

    public IngredientApiController(IngredientService service) {
        this._service = service;
    }

    @GetMapping
    public List<IngredientModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public IngredientModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public IngredientModel createModel(@Valid @RequestBody IngredientModel ingredient) {
        return _service.create(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public IngredientModel updateModel(@PathVariable Long id, @Valid @RequestBody IngredientModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
