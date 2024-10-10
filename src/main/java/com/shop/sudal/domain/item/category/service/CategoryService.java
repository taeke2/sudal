package com.shop.sudal.domain.item.category.service;

import com.shop.sudal.domain.entity.Category;
import com.shop.sudal.domain.item.category.model.CreateCategoryRequest;
import com.shop.sudal.domain.item.category.repository.CategoryRepository;
import com.shop.sudal.global.exception.CategoryException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CreateCategoryRequest createCategoryRequest) {
        if (categoryRepository.existsByName(createCategoryRequest.getName())) {
            throw new CategoryException(ResponseCode.CATEGORY_ALREADY_EXIST);
        }

        if (!categoryRepository.existsById(createCategoryRequest.getParentCategory().getId())) {
            throw new CategoryException(ResponseCode.CATEGORY_PARENT_NOT_FOUND);
        }

        categoryRepository.save(createCategoryRequest.toEntityCategory());
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ResponseCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
