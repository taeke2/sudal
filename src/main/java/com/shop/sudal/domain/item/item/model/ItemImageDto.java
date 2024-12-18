package com.shop.sudal.domain.item.item.model;

import com.shop.sudal.domain.entity.Item;
import com.shop.sudal.domain.entity.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDto {
    private Item item;
    private Integer seq;
    private String url;

    public ItemImage toEntityItemImage() {
        return ItemImage.builder()
                .item(item)
                .seq(seq)
                .url(url)
                .build();
    }
}
