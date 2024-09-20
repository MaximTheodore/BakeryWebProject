package com.example.bakery.controller;

import com.example.bakery.model.ClientModel;
import com.example.bakery.model.OrderModel;
import com.example.bakery.model.ProductModel;
import com.example.bakery.service.api.ClientRestApi;
import com.example.bakery.service.api.OrderRestApi;
import com.example.bakery.service.api.ProductRestApi;
import com.example.bakery.service.service.ClientService;
import com.example.bakery.service.service.OrderService;
import com.example.bakery.service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService _service;
    @Autowired
    private ClientService _clientService;
    @Autowired
    private ProductService _productService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<OrderModel> orders = _service.findAll();
        List<ClientModel> clients = _clientService.findAll();
        List<ProductModel> products = _productService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("order", new OrderModel());
        model.addAttribute("clients", clients);
        model.addAttribute("client", new ClientModel());
        model.addAttribute("products", products);
        model.addAttribute("product", new ProductModel());
        return "OrderHtml/orderList";
    }


    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String createOrder(@Valid @ModelAttribute OrderModel order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<OrderModel> orders = _service.findAll();
            List<ClientModel> clients = _clientService.findAll();
            List<ProductModel> products = _productService.findAll();
            model.addAttribute("order", order);
            model.addAttribute("orders", orders);
            model.addAttribute("clients", clients);
            model.addAttribute("products", products);
            model.addAttribute("client", new ClientModel());
            model.addAttribute("product", new ProductModel());
            return "OrderHtml/orderList";
        }
        _service.create(order);
        return "redirect:/order";
    }


    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String editOrder(@PathVariable Long id, Model model) {
        OrderModel order = _service.findById(id);
        List<ClientModel> clients = _clientService.findAll();
        List<ProductModel> products = _productService.findAll();
        model.addAttribute("order", order);
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        return "OrderHtml/orderEdit";
    }


    @PostMapping("/update")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String updateOrder(@RequestParam("id") Long id, @Valid @ModelAttribute OrderModel order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<OrderModel> orders = _service.findAll();
            List<ClientModel> clients = _clientService.findAll();
            List<ProductModel> products = _productService.findAll();
            model.addAttribute("order", order);
            model.addAttribute("orders", orders);
            model.addAttribute("clients", clients);
            model.addAttribute("client", new ClientModel());
            model.addAttribute("products", products);
            model.addAttribute("product", new ProductModel());
            return "OrderHtml/orderEdit";
        }
        order.setId(id);
        _service.update(order);
        return "redirect:/order";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String deleteOrder(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/order";
    }
}
