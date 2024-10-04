package com.shop.sudal.domain.address.controller;

import com.shop.sudal.domain.address.model.AddressDto;
import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.model.UpdateAddressRequest;
import com.shop.sudal.domain.address.service.AddressService;
import com.shop.sudal.global.response.ResponseCode;
import com.shop.sudal.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @Secured({"ROLE_MEMBER"})
    public ResponseCustom<List<AddressDto>> getAddressList() {
        return ResponseCustom.response(addressService.getAddressList(), ResponseCode.ADDRESS_LIST_READ_SUCCESS);
    }

    @PatchMapping("/{id}")
    @Secured({"ROLE_MEMBER"})
    public ResponseCustom<Void> updateAddress(@PathVariable Long id,
                                              @RequestBody UpdateAddressRequest updateAddressRequest) {
        addressService.updateAddress(id, updateAddressRequest);
        return ResponseCustom.responseNoData(ResponseCode.ADDRESS_UPDATE_SUCCESS);
    }
}
