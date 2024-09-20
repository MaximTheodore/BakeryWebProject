package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;

@Entity
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название категории должно быть от 2 до 50 символов")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<IngredientModel> ingredients;


    public @NotEmpty(message = "Поле не должно быть пустым") @Size(min = 2, max = 50,
            message = "Название категории должно быть от 2 до 50 символов") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Поле не должно быть пустым")
                        @Size(min = 2, max = 50, message = "Название категории должно быть от 2 до 50 символов")
                        String name) {
        this.name = name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

