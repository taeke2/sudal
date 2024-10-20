package com.shop.sudal.domain.item.item.model;

import com.shop.sudal.domain.entity.Category;
import com.shop.sudal.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;

    public Item toEntityItem() {
        return Item.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();
    }
}
