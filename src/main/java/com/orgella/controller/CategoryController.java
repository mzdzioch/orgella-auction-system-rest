package com.orgella.controller;

import com.orgella.model.Category;
import com.orgella.model.dto.CategoryDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category/")
@CrossOrigin
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseMessage<Category> saveCategory(@RequestBody CategoryDto categoryDto){

        if(categoryService.isCategoryExist(categoryDto.getCategoryName())){
            return new ResponseMessage<>(
                    StatusResponse.REQUEST_ERROR,
                    "Category already exists.",
                    null
            );
        }
        Optional<Category> parent = categoryService.findCategoryByName(categoryDto.getParentName());

        if(parent.isPresent()){
            Category savedCategory = categoryService.save(new Category(
                    categoryDto.getCategoryName(),
                    parent.get()
                    ));

            return new ResponseMessage<>(
                    StatusResponse.OK,
                    null,
                    savedCategory
            );
        }

        return new ResponseMessage<>(
                StatusResponse.REQUEST_ERROR,
                "Something went wrong...",
                null
        );
    }


    @RequestMapping(path = "/get/{categoryName}", method = RequestMethod.GET)
    public ResponseMessage<Category> getListOfCategoriesUnderCategory(@PathVariable("categoryName") String categoryName){

        Optional<Category> categoryOptional = categoryService.findCategoryByName(categoryName);

        if(categoryOptional.isPresent()){
            return new ResponseMessage<>(
                    StatusResponse.OK,
                    null,
                    categoryOptional.get()
            );
        }

        return new ResponseMessage<>(
                StatusResponse.REQUEST_ERROR,
                "The category does not exist or name is not correct",
                null
        );
    }


    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public ResponseMessage<Category> getRootList(){
        Optional<Category> rootCategory = categoryService.findCategoryByName("ROOT");


        if(rootCategory.isPresent()){
            return new ResponseMessage<>(
                    StatusResponse.OK,
                    null,
                    rootCategory.get()
            );
        }

        return new ResponseMessage<>(
                StatusResponse.OK,
                "The list is null or it occurs a problem.",
                null
        );
    }


    @RequestMapping(path = "/delete/{categoryName}", method = RequestMethod.DELETE)
    public ResponseMessage<Category> deleteCategory(@PathVariable("categoryName") String categoryName){

        Optional<Category> categoryOptional = categoryService.findCategoryByName(categoryName);

        if (categoryOptional.isPresent()){

            categoryService.delete(categoryOptional.get());

            return new ResponseMessage<>(
                    StatusResponse.OK,
                    categoryName + " has been deleted",
                    categoryOptional.get()
            );
        }

        return new ResponseMessage<>(
                StatusResponse.REQUEST_ERROR,
                "Given category does not exist",
                categoryOptional.get()
        );

    }





}
