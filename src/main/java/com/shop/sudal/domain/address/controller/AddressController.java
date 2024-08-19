package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/members/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @Secured({"ROLE_MEMBER"})
    public ResponseCustom<Void> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        addressService.createAddress(createAddressRequest);
        return ResponseCustom.responseNoData(ResponseCode.ADDRESS_CREATE_SUCCESS);
    }

}
