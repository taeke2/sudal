package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.member.auth.model.MemberDetails;
import com.shop.sudal.global.common.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class AddressService {

    private final ValidationService validationService;
    private final AddressRepository addressRepository;

    @Secured("ROLE_MEMBER")
    public Void createAddress(CreateAddressRequest createAddressRequest) {
        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long memberId = memberDetails.getAuthMemberDto().getId();
        Member member = validationService.validateMemberById(memberId);
        addressRepository.save(createAddressRequest.toEntityAddress(member));
        return null;
    }

}