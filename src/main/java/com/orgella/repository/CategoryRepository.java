package com.orgella.repository;

import com.orgella.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findAllBy();

    List<Category> findCategoriesByParentId(Integer parentId);

    Category findCategoryById(Integer id);

    Category findCategoryByName(String categoryName);

    Boolean existsCategoryById(Integer id);

    Boolean existsCategoryByName(String categoryName);




    //@Query("select new com.orgella.model.Category(lev1, lev2, lev3, lev4) from category as ")

}
