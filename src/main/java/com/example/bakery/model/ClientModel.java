package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя клиента должно быть от 2 до 50 символов")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
            message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX")
    private String phoneNumber;


    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<OrderModel> orders;

    private boolean isDeleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя клиента должно быть от 2 до 50 символов") String getName() {
        return name;
    }

    public void setName( @NotEmpty(message = "Поле не должно быть пустым")
                         @Size(min = 2, max = 50, message = "Имя клиента должно быть от 2 до 50 символов") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
            message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty(message = "Поле не должно быть пустым")
                               @Pattern(regexp = "((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}",
                                       message = "Номер телефона должен выглядеть так +7(XXX)XXX-XX-XX или так 8(XXX)XXX-XX-XX") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

}
