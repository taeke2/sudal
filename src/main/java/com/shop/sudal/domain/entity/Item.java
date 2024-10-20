package com.shop.sudal.domain.entity;

import com.shop.sudal.domain.item.category.model.ItemCategoryDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCategory> categories = new ArrayList<>();

    public void initCategories() {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        ItemCategory itemCategory = new ItemCategoryDto(this, category).toEntityItemCategory();
        categories.add(itemCategory);
    }

    @Builder
    public Item(Long id, String name, String description, Double price, Integer stock) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("item name cannot be null");
        if (price < 0) throw new IllegalArgumentException("price must be greater than or equal to 0");
        if (stock < 0) throw new IllegalArgumentException("stock must be greater than or equal to 0");

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
