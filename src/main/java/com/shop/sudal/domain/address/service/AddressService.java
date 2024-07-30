package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.AddressRequest;
import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.entity.User;
import com.shop.sudal.global.common.service.CommonValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AddressService {

    private final CommonValidationService validationService;
    private final AddressRepository addressRepository;

    @Transactional
    public String addAddress(Long userId, AddressRequest addressRequest) {
        User user = validationService.validateUserById(userId, "User not found with userId");
        addressRepository.save(addressRequest.toAddressEntity(user));
        return "Success add Address";
    }

}
