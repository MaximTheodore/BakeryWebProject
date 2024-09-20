package com.example.bakery.service.Impl;


import com.example.bakery.model.ClientModel;
import com.example.bakery.repository.ClientRepository;
import com.example.bakery.service.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;


    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientModel> findAll() {
        return repository.findAll();
    }

    @Override
    public ClientModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ClientModel create(ClientModel model) {
        return repository.save(model);
    }

    @Override
    public ClientModel update(ClientModel model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
