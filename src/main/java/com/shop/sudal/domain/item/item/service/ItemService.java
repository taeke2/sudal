package com.shop.sudal.domain.item.item.service;

import com.shop.sudal.domain.entity.Item;
import com.shop.sudal.domain.item.item.model.CreateItemRequest;
import com.shop.sudal.domain.item.item.repository.ItemRepository;
import com.shop.sudal.global.exception.ItemException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;

    public void createItem(CreateItemRequest createItemRequest) {
        if (itemRepository.existsByName(createItemRequest.getName())) {
            throw new ItemException(ResponseCode.ITEM_ALREADY_EXIST);
        }

        Item item = createItemRequest.toEntityItem();

        item.initCategories();
        item.addCategory(createItemRequest.getCategory());

        itemRepository.save(item);
    }
}
