package com.example.bakery.controller.api;


import com.example.bakery.model.CategoryModel;
import com.example.bakery.model.IngredientModel;
import com.example.bakery.service.service.CategoryService;
import com.example.bakery.service.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryApiController {

    private final CategoryService _service;
    private final IngredientService _ingredientService;

    public CategoryApiController(CategoryService service, IngredientService ingredientService) {
        this._service = service;
        this._ingredientService = ingredientService;
    }

    @GetMapping
    public List<CategoryModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public CategoryModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public CategoryModel createModel(@Valid @RequestBody CategoryModel category) {
        return _service.create(category);
    }

    @GetMapping("/{id}/ingredients")
    public String getCategoryIngredients(@PathVariable Long id, Model model) {
        CategoryModel category = _service.findById(id);
        List<IngredientModel> ingredients = _ingredientService.findByCategory(category);
        model.addAttribute("category", category);
        model.addAttribute("ingredients", ingredients);
        return "CategoryHtml/categoryIngredients";
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryModel updateModel(@PathVariable Long id, @Valid @RequestBody CategoryModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
