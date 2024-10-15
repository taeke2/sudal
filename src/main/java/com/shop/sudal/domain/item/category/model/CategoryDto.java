package com.shop.sudal.domain.item.category.model;

import com.shop.sudal.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private Long parentCategoryId; // parent의 id만 저장
    private List<CategoryDto> childCategories; // 자식 카테고리도 DTO로 변환

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.parentCategoryId = category.getParentCategory() != null ? category.getParentCategory().getId() : null;
        this.childCategories = category.getChildCategories().stream()
                .map(CategoryDto::new)
                .toList();
    }
}

