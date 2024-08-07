package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.AddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
import com.shop.sudal.global.common.ResponseCode;
import com.shop.sudal.global.common.ResponseCustom;
import com.shop.sudal.global.common.response.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/member/{memberId}/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseCustom<Void> addAddress(@PathVariable Long memberId, @RequestBody AddressRequest addressRequest) {
        return ResponseCustom.success(addressService.addAddress(memberId, addressRequest), ResponseCode.ADDRESS_CREATE_SUCCESS.getMessage());
    }

}
