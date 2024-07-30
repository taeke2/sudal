package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.AddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
import com.shop.sudal.global.common.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user/{userId}/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public BasicResponse<String> addAddress(@PathVariable Long userId, @RequestBody AddressRequest addressRequest) {
        return new BasicResponse<>(addressService.addAddress(userId, addressRequest));
    }
}
