package com.example.bakery.service.api;


import com.example.bakery.model.OrderModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderRestApi {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public OrderRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderModel> findAll() {
        ResponseEntity<OrderModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/order",
                OrderModel[].class);
        return Arrays.asList(response.getBody());
    }

    public OrderModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/order/" + id, OrderModel.class);
    }

    public OrderModel create(OrderModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/order", model, OrderModel.class);
    }

    public void update(Long id, OrderModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/order/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/order/" + id);
    }
}
