package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.List;

@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название изделия должно быть от 2 до 50 символов")
    private String name;

    @DecimalMin(value = "2.0", message = "Минимальная цена изделия 2.0")
    @DecimalMax(value = "100.0", message = "Максимальная цена изделия 100.0")
    @Positive(message = "Цена не должна быть отрицательной")
    private double price;


    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeModel recipe;

    @ManyToMany(mappedBy = "products")
    private List<OrderModel> orders;


    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название изделия должно быть от 2 до 50 символов") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Поле не должно быть пустым")
                        @Size(min = 2, max = 50, message = "Название изделия должно быть от 2 до 50 символов") String name) {
        this.name = name;
    }

    public @DecimalMin(value = "2.0", message = "Минимальная цена изделия 2.0")
    @DecimalMax(value = "100.0", message = "Максимальная цена изделия 100.0")
    @Positive(message = "Цена не должна быть отрицательной") double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "2.0", message = "Минимальная цена изделия 2.0")
                         @DecimalMax(value = "100.0", message = "Максимальная цена изделия 100.0")
                         @Positive(message = "Цена не должна быть отрицательной") double price) {
        this.price = price;
    }



    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public RecipeModel getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeModel recipe) {
        this.recipe = recipe;
    }


}
