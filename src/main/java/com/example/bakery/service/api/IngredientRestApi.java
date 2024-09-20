package com.example.bakery.service.api;


import com.example.bakery.model.CategoryModel;
import com.example.bakery.model.IngredientModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IngredientRestApi {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public IngredientRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<IngredientModel> findAll() {
        ResponseEntity<IngredientModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/ingredient", IngredientModel[].class);
        return Arrays.asList(response.getBody());
    }

    public IngredientModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/ingredient/" + id, IngredientModel.class);
    }

    public IngredientModel create(IngredientModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/ingredient", model, IngredientModel.class);
    }

    public void update(Long id, IngredientModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/ingredient/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/ingredient/" + id);
    }

    public List<IngredientModel> findByCategory(CategoryModel category) {
        String url = apiBaseUrl + "/v1/api/category/"+ category.getId() + "ingredients" ;
        IngredientModel[] response = restTemplate.getForObject(url, IngredientModel[].class);
        return Arrays.asList(response);
    }
}
