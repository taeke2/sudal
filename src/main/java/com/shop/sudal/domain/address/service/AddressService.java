package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.global.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class AddressService {

    private final ValidationService validationService;
    private final AddressRepository addressRepository;

    public Void createAddress(CreateAddressRequest createAddressRequest) {
        Long memberId = validationService.validateMemberIdByToken();
        Member member = validationService.validateMemberById(memberId);
        addressRepository.save(createAddressRequest.toEntityAddress(member));
        return null;
    }

}