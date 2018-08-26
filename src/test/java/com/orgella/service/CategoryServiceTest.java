package com.orgella.service;

import com.orgella.model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void shouldReturnedSavedCategory(){
        Optional<Category> optionalCategory = categoryService.findCategoryByName("Motors");

        if(optionalCategory.isPresent()){
            Category category = categoryService.save(new Category("Bus", optionalCategory.get()));
            Assert.assertNotNull(category);
        }
    }

//    @Test //TODO
//    public void shouldReturnedSavedCategoryAsChildrenForItsParent(){
//        Optional<Category> optionalCategory = categoryService.findCategoryByName("Motors");
//
//        if(optionalCategory.isPresent()){
//            Category expectedCategory = categoryService.save(new Category("Bus", optionalCategory.get()));
//
//            Optional<Category> parentOptional = categoryService.findCategoryByName("Motors");
//
//            if(parentOptional.isPresent()){
//                for (Category category : parentOptional.get().getChildren()) {
//                    System.out.println(category.getCategoryName());
//                }
//                Assert.assertEquals(expectedCategory, parentOptional.get().getChildren().contains(expectedCategory));
//            }
//        }
//    }

    @Test
    public void shouldReturnFalseIfCategoryAndItsChildrenAreDelete(){
        Optional<Category> optionalCategory = categoryService.findCategoryByName("Motors");

        if(optionalCategory.isPresent()){
            categoryService.delete(optionalCategory.get());
        }

        Optional<Category> result = categoryService.findCategoryByName("Motors");
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldReturnListOfRootCategories() {

        Optional<List<Category>> root = categoryService.findAllRootCategory();

        if(root.isPresent()){
            for (Category category : root.get()) {
                Assert.assertTrue(category.getCategoryName().equals("ROOT"));
            }
        }
    }

    @Test
    public void shouldReturnCategoryFindByCategoryName(){

        Optional<Category> category = categoryService.findCategoryByName("Parts");

        if(category.isPresent()){
            Assert.assertTrue(category.get().getCategoryName().equals("Parts"));
        }
    }

    @Test
    public void shouldReturnFalseForCategoryWhichDoesNotExist(){

        Optional<Category> category = categoryService.findCategoryByName("Part");

        Assert.assertFalse(category.isPresent());
    }

    @Test
    public void shouldReturnTrueIfCategoryExist() {

        Assert.assertTrue(categoryService.isCategoryExist("Motors"));
    }

    @Test
    public void shouldReturnFalseIfCategoryExist() {

        Assert.assertFalse(categoryService.isCategoryExist("Motor"));
    }

}