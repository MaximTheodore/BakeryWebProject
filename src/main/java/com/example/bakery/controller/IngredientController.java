package com.example.bakery.controller;

import com.example.bakery.model.CategoryModel;
import com.example.bakery.model.IngredientModel;
import com.example.bakery.service.api.CategoryRestApi;
import com.example.bakery.service.api.IngredientRestApi;
import com.example.bakery.service.service.CategoryService;
import com.example.bakery.service.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingredient")
@PreAuthorize("hasAuthority('EMPLOYEE')")
public class IngredientController {

    @Autowired
    private IngredientService _service;
    @Autowired
    private CategoryService _categoryService;

    @GetMapping
    public String getAllIngredients(Model model) {
        List<IngredientModel> ingredients = _service.findAll();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("ingredient", new IngredientModel());
        model.addAttribute("categories", _categoryService.findAll());
        model.addAttribute("category", new CategoryModel());
        return "IngredientHtml/ingredientList";
    }

    @PostMapping
    public String createIngredient(@Valid @ModelAttribute IngredientModel ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<IngredientModel> ingredients = _service.findAll();
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("ingredients", ingredients);
            return "IngredientHtml/ingredientList";
        }
        _service.create(ingredient);
        return "redirect:/ingredient";
    }

    @GetMapping("/edit/{id}")
    public String editIngredient(@PathVariable Long id, Model model) {
        IngredientModel ingredient = _service.findById(id);
        model.addAttribute("ingredient", ingredient);

        model.addAttribute("categories", _categoryService.findAll());
        model.addAttribute("category", new CategoryModel());

        return "IngredientHtml/ingredientEdit";
    }

    @PostMapping("/update")
    public String updateIngredient(@RequestParam("id") Long id, @Valid @ModelAttribute IngredientModel ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<IngredientModel> ingredients = _service.findAll();
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("ingredient", ingredient);
            return "IngredientHtml/ingredientList";
        }
        ingredient.setId(id);
        _service.update(ingredient);
        return "redirect:/ingredient";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/ingredient";
    }
}
