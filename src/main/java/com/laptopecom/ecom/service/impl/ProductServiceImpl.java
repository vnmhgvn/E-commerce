package com.laptopecom.ecom.service.impl;

import com.laptopecom.ecom.model.Product;
import com.laptopecom.ecom.repository.ProductRepository;
import com.laptopecom.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductByID(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductByID(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProductByCategoryID(int id){
        return productRepository.findAllByCategoryId(id);
    }

    public List<Product> getAllProductByManufacturerID(int id){
        return productRepository.findAllByManufacturerId(id);
    }
}
