package com.example.bakery.service.api;


import com.example.bakery.model.ProductModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductRestApi {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public ProductRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductModel> findAll() {
        ResponseEntity<ProductModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/product",
                ProductModel[].class);
        return Arrays.asList(response.getBody());
    }

    public ProductModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/product/" + id, ProductModel.class);
    }

    public ProductModel create(ProductModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/product", model, ProductModel.class);
    }

    public void update(Long id, ProductModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/product/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/product/" + id);
    }
}
