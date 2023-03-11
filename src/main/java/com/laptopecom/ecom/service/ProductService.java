package com.laptopecom.ecom.service;

import com.laptopecom.ecom.model.Product;
import com.laptopecom.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> getAllProduct() ;

    public void addProduct(Product product);

    public void deleteProductByID(Long id);

    public Optional<Product> getProductByID(Long id);

    public List<Product> getAllProductByCategoryID(int id);

    public List<Product> getAllProductByManufacturerID(int id);
}