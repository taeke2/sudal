package com.shop.sudal.domain.item.category.model;

import com.shop.sudal.domain.entity.Category;
import com.shop.sudal.domain.entity.Item;
import com.shop.sudal.domain.entity.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDto {
    private Item item;
    private Category category;

    public ItemCategory toEntityItemCategory() {
        return ItemCategory.builder()
                .item(item)
                .category(category)
                .build();
    }
}
