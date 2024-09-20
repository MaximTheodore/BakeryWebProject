package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 50, message = "Название поставщика от 1 до 50 символов")
    @NotBlank(message = "Введить название поставщика")
    private String supplierName;
    @Size(min = 5, max = 50, message = "Адрес поставщика от 1 до 50 символов")
    @NotBlank(message = "Введить адрес поставщика")
    private String supplierAddress;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
            message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX")
    private String supplierPhone;
    @Size(min = 1, max = 50, message = "Почта поставщика от 1 до 50 символов")
    @NotBlank(message = "Введить почту поставщика")
    @Email(message = "Неправильно введена почта")
    private String supplierEmail;
    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable(
            name = "supplier_ingredient",
            joinColumns = @JoinColumn(name ="ingredient_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id",
                    referencedColumnName = "id")
    )
    @JsonBackReference
    private List<IngredientModel> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 1, max = 50, message = "Название поставщика от 1 до 50 символов") @NotBlank(message = "Введить название поставщика") String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(@Size(min = 1, max = 50, message = "Название поставщика от 1 до 50 символов") @NotBlank(message = "Введить название поставщика") String supplierName) {
        this.supplierName = supplierName;
    }

    public @Size(min = 5, max = 50, message = "Адрес поставщика от 1 до 50 символов") @NotBlank(message = "Введить адрес поставщика") String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(@Size(min = 5, max = 50, message = "Адрес поставщика от 1 до 50 символов") @NotBlank(message = "Введить адрес поставщика") String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public  @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
            message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX")String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone( @NotEmpty(message = "Поле не должно быть пустым")
                                  @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
                                          message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX") String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public @Size(min = 1, max = 50, message = "Почта поставщика от 1 до 50 символов") @NotBlank(message = "Введить почту поставщика") @Email(message = "Неправильно введена почта") String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(@Size(min = 1, max = 50, message = "Почта поставщика от 1 до 50 символов") @NotBlank(message = "Введить почту поставщика") @Email(message = "Неправильно введена почта") String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }
}
