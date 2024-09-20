package com.example.bakery.controller.api;

import com.example.bakery.model.ClientModel;
import com.example.bakery.service.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
public class ClientApiController {

    private final ClientService _service;

    public ClientApiController(ClientService service) {
        this._service = service;
    }

    @GetMapping
    public List<ClientModel> getAllModels() {
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public ClientModel getModelById(@PathVariable Long id) {
        return _service.findById(id);
    }

    @PostMapping
    public ClientModel createModel(@Valid @RequestBody ClientModel client) {
        return _service.create(client);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        _service.delete(id);
    }

    @PutMapping("/{id}")
    public ClientModel updateModel(@PathVariable Long id, @Valid @RequestBody ClientModel model) {
        model.setId(id);
        return _service.update(model);
    }
}
