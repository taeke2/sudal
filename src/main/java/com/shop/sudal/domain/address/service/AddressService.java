package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.AddressRequest;
import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.entity.Member;
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
    public String addAddress(Long memberId, AddressRequest addressRequest) {
        Member member = validationService.validateMemberById(memberId, "Member not found with memberId");
        addressRepository.save(addressRequest.toAddressEntity(member));
        return "Success add Address";
    }

}
