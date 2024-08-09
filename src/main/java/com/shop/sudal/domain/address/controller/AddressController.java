package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
import com.shop.sudal.global.common.response.ResponseCode;
import com.shop.sudal.global.common.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/member/{memberId}/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseCustom<Void> addAddress(@PathVariable Long memberId, @RequestBody CreateAddressRequest createAddressRequest) {
        return ResponseCustom.success(addressService.addAddress(memberId, createAddressRequest), ResponseCode.ADDRESS_CREATE_SUCCESS.getMessage());
    }

}
