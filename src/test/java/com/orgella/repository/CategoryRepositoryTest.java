package com.orgella.repository;

import com.orgella.model.Auction;
import com.orgella.model.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {

        categoryRepository.save(new Category("Electronics", 0));

        categoryRepository.save(new Category("Motors", 0));
        categoryRepository.save(new Category("Clothes", 0));



        categoryRepository.save(new Category("Laptops", 1));
        categoryRepository.save(new Category("PC", 1));
        categoryRepository.save(new Category("Servers", 1));
        //adding subcategories for Motors category
        categoryRepository.save(new Category("Parts", 2));
        categoryRepository.save(new Category("Cars", 2));
        categoryRepository.save(new Category("Vehicles", 2));
        categoryRepository.save(new Category("Trucks", 2));
        //adding subcategories for Clothes category
        categoryRepository.save(new Category("Women Clothing", 3));
        categoryRepository.save(new Category("Men Clothing", 3));
        categoryRepository.save(new Category("Shoes", 3));
    }

    @Test
    public void findAllBy() {

    }

    @Test
    public void findCategoriesByParentId() {
        List<Category> categoriesEl = new ArrayList<>();
        List<Category> categoriesCa = new ArrayList<>();
        List<Category> categoriesCl = new ArrayList<>();


        categoriesEl = categoryRepository.findCategoriesByParentId(1);
        categoriesCa = categoryRepository.findCategoriesByParentId(2);
        categoriesCl = categoryRepository.findCategoriesByParentId(3);

        assertTrue(categoriesEl.size()==3);
        assertTrue(categoriesCa.size()==4);
        assertTrue(categoriesCl.size()==3);

    }

    @Test
    public void findCategoryById() {
        assertNotNull(categoryRepository.findCategoryById(4));
    }

    @Test
    public void shouldReturnFalseIfCategoryExists() {
        assertTrue(categoryRepository.existsCategoryById(1));
        assertTrue(categoryRepository.existsCategoryById(13));

    }

    @Test
    public void shouldReturnFalseIfCategoryDoesNotExist() {
        assertFalse(categoryRepository.existsCategoryById(14));
    }
}