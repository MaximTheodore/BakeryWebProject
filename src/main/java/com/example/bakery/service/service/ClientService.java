package com.example.bakery.service.service;


import com.example.bakery.model.ClientModel;

import java.util.List;

public interface ClientService {
    public List<ClientModel> findAll();
    public ClientModel findById(Long id);
    public ClientModel create(ClientModel model);
    public ClientModel update(ClientModel model);
    public void delete(Long id);



}
