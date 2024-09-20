package com.example.bakery.controller;

import com.example.bakery.model.SupplierModel;
import com.example.bakery.service.api.SupplierRestApi;
import com.example.bakery.service.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService _service;

    @GetMapping
    public String getAllSuppliers(Model model) {
        List<SupplierModel> suppliers = _service.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("supplier", new SupplierModel());
        return "SupplierHtml/supplierList";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public String createSupplier(@Valid @ModelAttribute SupplierModel supplier, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SupplierModel> suppliers = _service.findAll();
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("supplier", supplier);
            return "SupplierHtml/supplierList";
        }
        _service.create(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String editSupplier(@PathVariable Long id, Model model) {
        SupplierModel supplier = _service.findById(id);
        model.addAttribute("supplier", supplier);
        return "SupplierHtml/supplierEdit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String updateSupplier(@RequestParam("id") Long id, @Valid @ModelAttribute SupplierModel supplier, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SupplierModel> suppliers = _service.findAll();
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("supplier", supplier);
            return "SupplierHtml/supplierList";
        }
        supplier.setId(id);
        _service.update(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String deleteSupplier(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/supplier";
    }
}
