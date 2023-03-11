package com.laptopecom.ecom.service.impl;

import com.laptopecom.ecom.model.Category;
import com.laptopecom.ecom.repository.CategoryRepository;
import com.laptopecom.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> checkCategoryById(int id){
        return categoryRepository.findById(id);
    }
}
