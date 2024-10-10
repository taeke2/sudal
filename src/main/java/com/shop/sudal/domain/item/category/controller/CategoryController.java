package com.shop.sudal.domain.item.category.controller;

import com.shop.sudal.domain.item.category.model.CreateCategoryRequest;
import com.shop.sudal.domain.item.category.service.CategoryService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseCustom<Void> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        categoryService.createCategory(createCategoryRequest);
        return ResponseCustom.responseNoData(ResponseCode.CATEGORY_CREATE_SUCCESS);
    }
}
