package com.example.bakery.controller.api;

import com.example.bakery.model.RecipeModel;
import com.example.bakery.service.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/recipe")
public class RecipeApiController {

    private final RecipeService _service;

    public RecipeApiController(RecipeService service) {
        this._service = service;
    }

    @GetMapping
    public List<RecipeModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public RecipeModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public RecipeModel createModel(@Valid @RequestBody RecipeModel recipe) {
        return _service.create(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public RecipeModel updateModel(@PathVariable Long id, @Valid @RequestBody RecipeModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
