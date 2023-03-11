package com.laptopecom.ecom.service;

import com.laptopecom.ecom.model.Category;
import com.laptopecom.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    public List<Category> getAllCategory();
    public void addCategory(Category category);
    public void deleteCategoryById(int id);
    public Optional<Category> checkCategoryById(int id);
}
