package com.shop.sudal.domain.item.category.model;

import com.shop.sudal.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    private String name;
    private String description;
    private Category parentCategory;

    public Category toEntityCategory() {
        return Category.builder()
                .name(name)
                .description(description)
                .parentCategory(parentCategory)
                .build();
    }
}
