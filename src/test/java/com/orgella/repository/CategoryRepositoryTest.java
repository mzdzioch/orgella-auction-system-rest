package com.orgella.repository;

import com.orgella.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReturnListOfRootsWhichParentIsNull(){
        Optional<List<Category>> categories = categoryRepository.findAllByParentIsNull();

        for (Category category : categories.get()) {
            Assert.assertTrue(category.getCategoryName().equals("ROOT"));
        }
    }

    @Test
    public void shouldReturnCategoryFindByName() {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByCategoryName("Electronics");

        if(categoryOptional.isPresent()) {
            Category categoryResult = categoryOptional.get();
            Assert.assertEquals(categoryResult.getCategoryName(), "Electronics");
        }
    }

    @Test
    public void shouldReturnTrueIfCategoryExist(){
        assertTrue(categoryRepository.existsCategoryByCategoryName("Clothes"));
    }

    @Test
    public void shouldReturnFalseIfCategoryExist(){
        assertFalse(categoryRepository.existsCategoryByCategoryName("Cloth"));
    }

}