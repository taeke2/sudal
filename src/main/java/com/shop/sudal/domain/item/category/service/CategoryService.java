package com.shop.sudal.domain.item.category.service;

import com.shop.sudal.domain.entity.Category;
import com.shop.sudal.domain.item.category.model.CategoryDto;
import com.shop.sudal.domain.item.category.model.CreateCategoryRequest;
import com.shop.sudal.domain.item.category.model.UpdateCategoryRequest;
import com.shop.sudal.domain.item.category.repository.CategoryRepository;
import com.shop.sudal.global.exception.CategoryException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findByParentCategoryIsNull();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(new CategoryDto(category));
        }
        return categoryDtos;
    }

    public void createCategory(CreateCategoryRequest createCategoryRequest) {
        if (createCategoryRequest.getParentCategory() != null &&
                !categoryRepository.existsById(createCategoryRequest.getParentCategory().getId())) {
            throw new CategoryException(ResponseCode.CATEGORY_PARENT_NOT_FOUND);
        }

        categoryRepository.save(createCategoryRequest.toEntityCategory());
    }

    public void updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        if (updateCategoryRequest.getParentCategory() != null &&
                !categoryRepository.existsById(updateCategoryRequest.getParentCategory().getId())) {
            throw new CategoryException(ResponseCode.CATEGORY_PARENT_NOT_FOUND);
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ResponseCode.CATEGORY_NOT_FOUND));

        category.updateCategory(updateCategoryRequest);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ResponseCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
