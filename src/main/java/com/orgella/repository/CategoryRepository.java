package com.orgella.repository;

import com.orgella.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<List<Category>> findAllByParentIsNull();

    Optional<Category> findCategoryByCategoryName(String categoryName);

    Boolean existsCategoryByCategoryName(String categoryName);

}
