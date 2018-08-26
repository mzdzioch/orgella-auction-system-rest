package com.orgella.service;

import com.orgella.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    Category save(Category category);

    void delete(Category category);

    Optional<List<Category>> findAllRootCategory();

    Optional<Category> findCategoryByName(String name);

    Boolean isCategoryExist(String name);

}
