package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
public class RecipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 255, message = "Описание рецепта должно быть от 2 до 255 символов")
    private String description;

    @OneToOne(mappedBy = "recipe")
    @JsonManagedReference
    private ProductModel product;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonManagedReference
    @JsonIgnore
    private Collection<IngredientModel> ingredients;
    private boolean isDeleted = false;

    public Collection<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 255, message = "Описание рецепта должно быть от 2 до 255 символов")
    String getDescription() {
        return description;
    }

    public void setDescription(
            @NotEmpty(message = "Поле не должно быть пустым")
            @Size(min = 2, max = 255, message = "Описание рецепта должно быть от 2 до 255 символов")
            String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }



}
