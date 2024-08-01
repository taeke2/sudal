package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.AddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
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
    public SimpleResponse<String> addAddress(@PathVariable Long memberId, @RequestBody AddressRequest addressRequest) {
        return new SimpleResponse<>(addressService.addAddress(memberId, addressRequest));
    }

}
