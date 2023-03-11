package com.laptopecom.ecom.repository;

import com.laptopecom.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findAllByCategoryId(int id);
    public List<Product> findAllByManufacturerId(int id);
}
