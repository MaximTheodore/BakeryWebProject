package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Category;


import java.util.List;

@Entity
public class IngredientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название ингредиента должно быть от 2 до 50 символов")
    private String name;

    @Min(value = 0, message = "Минимальное количество ингредиентов 0")
    @Max(value = 100000, message = "Максимальное количество ингредиентов 100000")
    @Positive(message = "Количество не должно быть отрицательным")
    private int quantity;

    private boolean isDeleted = false;

    @DecimalMin(value = "0.1", message = "Минимальная цена товара 0.1")
    @DecimalMax(value = "100000.0", message = "Максимальная цена товара 100000.0")
    @Positive(message = "Цена не должна быть отрицательной")
    private double PriceForOne;


    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeModel recipeModel;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryModel category;

    @ManyToMany(mappedBy = "ingredients")
    @JsonBackReference
    private List<SupplierModel> suppliers;

    @ManyToMany(mappedBy = "ingredients")
    private List<StorehouseModel> storehouse;


    public RecipeModel getRecipeModel() {
        return recipeModel;
    }

    public void setRecipeModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название ингредиента должно быть от 2 до 50 символов") String getName() {
        return name;
    }

    public void setName( @NotEmpty(message = "Поле не должно быть пустым")
                         @Size(min = 2, max = 50, message = "Название ингредиента должно быть от 2 до 50 символов") String name) {
        this.name = name;
    }


    public @DecimalMin(value = "0.1", message = "Минимальная цена товара 0.1")
    @DecimalMax(value = "100000.0", message = "Максимальная цена товара 100000.0")
    @Positive(message = "Цена не должна быть отрицательной") double getPriceForOne() {
        return PriceForOne;
    }

    public void setPriceForOne(@DecimalMin(value = "0.1", message = "Минимальная цена товара 0.1")
                               @DecimalMax(value = "100000.0", message = "Максимальная цена товара 100000.0")
                               @Positive(message = "Цена не должна быть отрицательной") double priceForOne) {
        PriceForOne = priceForOne;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<SupplierModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierModel> suppliers) {
        this.suppliers = suppliers;
    }

    public List<StorehouseModel> getStorehouse() {
        return storehouse;
    }

    public void setStorehouse(List<StorehouseModel> storehouse) {
        this.storehouse = storehouse;
    }

    public @Min(value = 0, message = "Минимальное количество ингредиентов 0")
    @Max(value = 100000, message = "Максимальное количество ингредиентов 100000")
    @Positive(message = "Количество не должно быть отрицательным")
    int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Минимальное количество ингредиентов 0")
                            @Max(value = 100000, message = "Максимальное количество ингредиентов 100000")
                            @Positive(message = "Количество не должно быть отрицательным")
                            int quantity) {
        this.quantity = quantity;
    }
}
