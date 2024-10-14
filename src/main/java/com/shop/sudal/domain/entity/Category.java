package com.shop.sudal.domain.entity;

import com.shop.sudal.domain.item.category.model.UpdateCategoryRequest;
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
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> childCategories = new ArrayList<>();

    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        this.name = updateCategoryRequest.getName();
        this.description = updateCategoryRequest.getDescription();
        this.parentCategory = updateCategoryRequest.getParentCategory();
    }

    @Builder
    public Category(Long id, String name, String description, Category parentCategory) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("category name cannot be null");

        this.id = id;
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
    }

}
