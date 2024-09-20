package com.example.bakery.service.api;


import com.example.bakery.model.StorehouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StorehouseRestApi {


    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public StorehouseRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<StorehouseModel> findAll() {
        ResponseEntity<StorehouseModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/storehouse",
                StorehouseModel[].class);
        return Arrays.asList(response.getBody());
    }

    public StorehouseModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/storehouse/" + id, StorehouseModel.class);
    }

    public StorehouseModel create(StorehouseModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/storehouse", model, StorehouseModel.class);
    }

    public void update(Long id, StorehouseModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/storehouse/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/storehouse/" + id);
    }
}
