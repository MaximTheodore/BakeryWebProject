package com.example.bakery.controller;

import com.example.bakery.model.IngredientModel;
import com.example.bakery.model.StorehouseModel;
import com.example.bakery.service.api.StorehouseRestApi;
import com.example.bakery.service.service.IngredientService;
import com.example.bakery.service.service.StorehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/storehouse")
@PreAuthorize("hasAnyAuthority('MANAGER', 'EMPLOYEE')")
public class StorehouseController {

    @Autowired
    private StorehouseService _service;
    @Autowired
    private IngredientService _ingredientService;

    @GetMapping
    public String getAllStorehouses(Model model) {
        List<StorehouseModel> storehouses = _service.findAll();
        List<IngredientModel> allIngredients = _ingredientService.findAll();
        model.addAttribute("storehouses", storehouses);
        model.addAttribute("storehouse", new StorehouseModel());
        model.addAttribute("allIngredients", allIngredients);
        return "StorehouseHtml/storehouseList";
    }

    @PostMapping
    public String createStorehouse(@Valid @ModelAttribute StorehouseModel storehouse, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<StorehouseModel> storehouses = _service.findAll();
            List<IngredientModel> allIngredients = _ingredientService.findAll();
            model.addAttribute("storehouses", storehouses);
            model.addAttribute("storehouse", storehouse);
            model.addAttribute("allIngredients", allIngredients);
            return "StorehouseHtml/storehouseList";
        }
        _service.create(storehouse);
        return "redirect:/storehouse";
    }

    @PostMapping("/update")
    public String updateStorehouse(@RequestParam("id") Long id, @Valid @ModelAttribute StorehouseModel storehouse, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<StorehouseModel> storehouses = _service.findAll();
            List<IngredientModel> allIngredients = _ingredientService.findAll();
            model.addAttribute("storehouses", storehouses);
            model.addAttribute("storehouse", storehouse);
            model.addAttribute("allIngredients", allIngredients);
            return "StorehouseHtml/storehouseEdit";
        }
        storehouse.setId(id);
        _service.update(storehouse);
        return "redirect:/storehouse";
    }


    @GetMapping("/edit/{id}")
    public String editStorehouse(@PathVariable Long id, Model model) {
        StorehouseModel storehouse = _service.findById(id);
        List<IngredientModel> allIngredients = _ingredientService.findAll();
        model.addAttribute("storehouse", storehouse);
        model.addAttribute("allIngredients", allIngredients);
        return "StorehouseHtml/storehouseEdit";
    }

    @GetMapping("/delete/{id}")
    public String deleteStorehouse(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/storehouse";
    }
}
