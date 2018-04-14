package com.orgella.service;

import com.orgella.model.Category;
import com.orgella.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {

        categoryService.save(new Category("Electronics", 0));

        categoryService.save(new Category("Motors", 0));
        categoryService.save(new Category("Clothes", 0));

        categoryService.save(new Category("Laptops", 1));
        categoryService.save(new Category("PC", 1));
        categoryService.save(new Category("Servers", 1));
        //adding subcategories for Motors category
        categoryService.save(new Category("Parts", 2));
        categoryService.save(new Category("Cars", 2));
        categoryService.save(new Category("Vehicles", 2));
        categoryService.save(new Category("Trucks", 2));
        //adding subcategories for Clothes category
        categoryService.save(new Category("Women Clothing", 3));
        categoryService.save(new Category("Men Clothing", 3));
        categoryService.save(new Category("Shoes", 3));
    }

    @Test
    public void createCategory() {
        categoryService.createCategory().getChildren().stream().forEach(p -> System.out.printf(p.getItem().getName()));


    }

    @Test
    public void findAllBy() {
    }

    @Test
    public void findCategoriesByParentId() {
    }

    @Test
    public void findCategoryById() {
    }

    @Test
    public void isParentExist() {
    }

    @Test
    public void getBuilder() {
    }

    @Test
    public void addCategory() {
    }

    @Test
    public void getCategoryAndSubcategoriesListId() {
    }

    @Test
    public void getRootCategories() {
    }
}