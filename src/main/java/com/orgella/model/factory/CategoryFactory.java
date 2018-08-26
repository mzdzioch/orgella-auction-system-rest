package com.orgella.model.factory;

import com.orgella.model.Category;
import com.orgella.model.dto.CategoryDto;

public class CategoryFactory {

    public static Category create(CategoryDto categoryDto, Category parent){
        return new Category(categoryDto.getCategoryName(),
                parent);
    }
}
