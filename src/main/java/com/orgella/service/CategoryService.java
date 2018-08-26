package com.orgella.service;

import com.orgella.model.Category;
import com.orgella.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        Category parent = category.getParent();

        if(parent != null) {
            parent.getChildren().add(category);
            categoryRepository.save(parent);
        }

        categoryRepository.save(category);

        return category;
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Optional<List<Category>> findAllRootCategory() {

        Optional<List<Category>> rootCategory = categoryRepository.findAllByParentIsNull();

        return rootCategory;
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findCategoryByCategoryName(name);
    }


    @Override
    public Boolean isCategoryExist(String categoryName) {
        return categoryRepository.existsCategoryByCategoryName(categoryName);
    }

}
