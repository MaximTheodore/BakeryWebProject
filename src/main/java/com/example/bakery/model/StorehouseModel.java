package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Set;

@Entity
public class StorehouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Минимальное количество полок 1")
    @Max(value = 10, message = "Максимальное количество полок 10")
    @Positive(message = "Количество не должно быть отрицательным")
    private int ShelfNumber;


    @DecimalMax(value = "1000000.0", message = "Максимальная цена товара 1000000.0")
    @Positive(message = "Цена не должна быть отрицательной")
    private double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "storehouse_ingredient",
            joinColumns = @JoinColumn(name = "storehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonManagedReference
    private Set<IngredientModel> ingredients;


    public Set<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }



    public @DecimalMax(value = "1000000.0", message = "Максимальная цена товара 1000000.0")
    @Positive(message = "Цена не должна быть отрицательной") double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@DecimalMin(value = "0.1", message = "Минимальная цена товара 0.1") @DecimalMax(value = "100000.0", message = "Максимальная цена товара 100000.0") @Positive(message = "Цена не должна быть отрицательной") double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public @Min(value = 1, message = "Минимальное количество полок 1")
    @Max(value = 10, message = "Максимальное количество полок 10")
    @Positive(message = "Количество не должно быть отрицательным")
    int getShelfNumber() {
        return ShelfNumber;
    }

    public void setShelfNumber(
            @Min(value = 1, message = "Минимальное количество полок 1")
            @Max(value = 10, message = "Максимальное количество полок 10")
            @Positive(message = "Количество не должно быть отрицательным")
            int shelfNumber) {
        ShelfNumber = shelfNumber;
    }



}
