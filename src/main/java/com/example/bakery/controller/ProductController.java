package com.example.bakery.controller;

import com.example.bakery.model.ProductModel;
import com.example.bakery.model.RecipeModel;
import com.example.bakery.service.api.ProductRestApi;
import com.example.bakery.service.api.RecipeRestApi;
import com.example.bakery.service.service.ProductService;
import com.example.bakery.service.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService _service;

    @Autowired
    private RecipeService _recipeService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductModel> products = _service.findAll();
        model.addAttribute("products", products);
        model.addAttribute("product", new ProductModel());


        model.addAttribute("recipes", _recipeService.findAll());
        model.addAttribute("recipe", new RecipeModel());


        return "ProductHtml/productList";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String createProduct(@Valid @ModelAttribute ProductModel product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ProductModel> products = _service.findAll();
            model.addAttribute("products", products);
            model.addAttribute("product", product);
            return "ProductHtml/productList";
        }
        _service.create(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String editProduct(@PathVariable Long id, Model model) {
        ProductModel product = _service.findById(id);
        List<RecipeModel> recipes = _recipeService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("recipes", recipes);
        return "ProductHtml/productEdit";
    }


    @PostMapping("/update")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String updateProduct(@RequestParam("id") Long id, @Valid @ModelAttribute ProductModel product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ProductModel> products = _service.findAll();
            model.addAttribute("products", products);
            model.addAttribute("product", product);
            return "ProductHtml/productList";
        }
        product.setId(id);
        _service.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String deleteProduct(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/product";
    }
}
