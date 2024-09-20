package com.example.bakery.service.api;


import com.example.bakery.model.SupplierModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierRestApi {

    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public SupplierRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<SupplierModel> findAll() {
        ResponseEntity<SupplierModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/supplier",
                SupplierModel[].class);
        return Arrays.asList(response.getBody());
    }

    public SupplierModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/supplier/" + id, SupplierModel.class);
    }

    public SupplierModel create(SupplierModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/supplier", model, SupplierModel.class);
    }

    public void update(Long id, SupplierModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/supplier/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/supplier/" + id);
    }
}
