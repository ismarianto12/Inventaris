package com.Inventori.Serivces;

import com.Inventori.Models.Category;
import com.Inventori.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriServiceImpl implements CategoriInterface{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return  categoryRepository.findAll();
    }

    @Override
    public Optional<Category> showByid(Long id) {
        return categoryRepository.findById(id);
    }
    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
