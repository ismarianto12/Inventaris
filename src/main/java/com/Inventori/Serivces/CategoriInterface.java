package com.Inventori.Serivces;

import com.Inventori.Models.Category;

import java.util.*;

public interface CategoriInterface {
    List<Category> getCategories();
    Optional<Category> showByid(Long id);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);

}
