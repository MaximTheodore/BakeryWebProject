package com.example.bakery.controller;

import com.example.bakery.model.ClientModel;
import com.example.bakery.service.api.ClientRestApi;
import com.example.bakery.service.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MANAGER')")

public class ClientController {

    @Autowired
    private ClientService _service;


    @GetMapping
    public String getAllClients(Model model) {
        List<ClientModel> clients = _service.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new ClientModel());
        return "ClientHtml/clientList";
    }

    @PostMapping
    public String createClient(@Valid @ModelAttribute ClientModel client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ClientModel> clients = _service.findAll();
            model.addAttribute("clients", clients);
            model.addAttribute("client", client);
            return "ClientHtml/clientList";
        }
        _service.create(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        ClientModel client = _service.findById(id);
        model.addAttribute("client", client);
        return "ClientHtml/clientEdit";
    }

    @PostMapping("/update")
    public String updateClient(@RequestParam("id") Long id, @Valid @ModelAttribute ClientModel client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ClientModel> clients = _service.findAll();
            model.addAttribute("clients", clients);
            model.addAttribute("client", client);
            return "ClientHtml/clientList";
        }
        client.setId(id);
        _service.update(client);
        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        _service.delete(id);
        return "redirect:/client";
    }
}
