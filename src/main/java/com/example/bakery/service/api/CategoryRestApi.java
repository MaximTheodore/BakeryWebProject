package com.example.bakery.service.api;

import com.example.bakery.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryRestApi {

    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public CategoryRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CategoryModel> findAll() {
        ResponseEntity<CategoryModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/category", CategoryModel[].class);
        return Arrays.asList(response.getBody());
    }

    public CategoryModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/category/" + id, CategoryModel.class);
    }

    public CategoryModel create(CategoryModel category) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/category", category, CategoryModel.class);
    }

    public void update(Long id, CategoryModel category) {
        restTemplate.put(apiBaseUrl + "/v1/api/category/" + id, category);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/category/" + id);
    }
}
