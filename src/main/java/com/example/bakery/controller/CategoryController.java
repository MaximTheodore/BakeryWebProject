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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER', 'CLIENT')")
    @GetMapping
    public String getAllCategory(Model model) {
        List<CategoryModel> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new CategoryModel());
        return "CategoryHtml/categoryList";
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER')")
    public String createCategory(@Valid @ModelAttribute CategoryModel category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryModel> categoryModels = categoryService.findAll();
            model.addAttribute("category", category);
            model.addAttribute("categories", categoryModels);
            return "CategoryHtml/categoryList";
        }
        categoryService.create(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER')")
    public String editCategory(@PathVariable Long id, Model model) {
        CategoryModel category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "CategoryHtml/categoryEdit";
    }

    @GetMapping("/{id}/ingredients")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER', 'CLIENT')")
    public String getCategoryIngredients(@PathVariable Long id, Model model) {
        CategoryModel category = categoryService.findById(id);
        List<IngredientModel> ingredients = ingredientService.findByCategory(category);
        model.addAttribute("category", category);
        model.addAttribute("ingredients", ingredients);
        return "CategoryHtml/categoryIngredients";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER')")
    public String updateCategory(@RequestParam("id") Long id, @Valid @ModelAttribute CategoryModel category,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryModel> categoryModels = categoryService.findAll();
            model.addAttribute("category", category);
            model.addAttribute("categories", categoryModels);
            return "CategoryHtml/categoryList";
        }
        category.setId(id);
        categoryService.update(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER')")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
