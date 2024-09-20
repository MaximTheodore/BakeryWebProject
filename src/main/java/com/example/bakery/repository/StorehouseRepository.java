package com.example.bakery.repository;

import com.example.bakery.model.StorehouseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorehouseRepository extends JpaRepository<StorehouseModel, Long> {
}
