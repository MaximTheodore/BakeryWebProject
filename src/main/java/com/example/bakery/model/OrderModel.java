package com.example.bakery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @Positive(message = "Стоимость заказа не должна быть отрицательной")
    private double totalCost;
    private boolean isDeleted = false;

    @ElementCollection(targetClass = ReadinessModel.class)
    @CollectionTable(name = "order_readiness", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "readiness")
    private Set<ReadinessModel> readness;


    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name ="product_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id",
                    referencedColumnName = "id")
    )
    private List<ProductModel> products;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private ClientModel client;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @DateTimeFormat(pattern = "yyyy-MM-dd") Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalСost() {
        return totalCost;
    }

    public void setTotalCost( double totalCost) {
        totalCost = totalCost;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<ReadinessModel> getReadness() {
        return readness;
    }

    public void setReadness(Set<ReadinessModel> readness) {
        this.readness = readness;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

}
