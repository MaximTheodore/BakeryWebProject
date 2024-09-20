package com.example.bakery.service.api;


import com.example.bakery.model.ClientModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientRestApi {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public ClientRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ClientModel> findAll() {
        ResponseEntity<ClientModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/client", ClientModel[].class);
        return Arrays.asList(response.getBody());
    }

    public ClientModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/client/" + id, ClientModel.class);
    }

    public ClientModel create(ClientModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/client", model, ClientModel.class);
    }

    public void update(Long id, ClientModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/client/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/client/" + id);
    }
}
