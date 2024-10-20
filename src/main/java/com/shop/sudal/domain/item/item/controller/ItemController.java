package com.shop.sudal.domain.item.item.controller;

import com.shop.sudal.domain.item.item.model.CreateItemRequest;
import com.shop.sudal.domain.item.item.service.ItemService;
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
@RequestMapping("/items")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseCustom<Void> createItem(@RequestBody CreateItemRequest createItemRequest) {
        itemService.createItem(createItemRequest);
        return ResponseCustom.responseNoData(ResponseCode.ITEM_CREATE_SUCCESS);
    }
}
