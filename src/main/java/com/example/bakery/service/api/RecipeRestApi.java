package com.example.bakery.service.api;


import com.example.bakery.model.RecipeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RecipeRestApi {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public RecipeRestApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<RecipeModel> findAll() {

        ResponseEntity<RecipeModel[]> response = restTemplate.getForEntity(apiBaseUrl + "/v1/api/recipe",
                RecipeModel[].class);
        return Collections.emptyList();
    }


    public RecipeModel findById(Long id) {
        return restTemplate.getForObject(apiBaseUrl + "/v1/api/recipe/" + id, RecipeModel.class);
    }

    public RecipeModel create(RecipeModel model) {
        return restTemplate.postForObject(apiBaseUrl + "/v1/api/recipe", model, RecipeModel.class);
    }

    public void update(Long id, RecipeModel model) {
        restTemplate.put(apiBaseUrl + "/v1/api/recipe/" + id, model);
    }

    public void delete(Long id) {
        restTemplate.delete(apiBaseUrl + "/v1/api/recipe/" + id);
    }

}
