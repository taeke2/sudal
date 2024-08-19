package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.AddressDto;
import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.repository.AddressRepository;
import com.shop.sudal.domain.entity.Address;
import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.global.common.ValidationService;
import com.shop.sudal.global.exception.AddressException;
import com.shop.sudal.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class AddressService {

    private final ValidationService validationService;
    private final AddressRepository addressRepository;

    public void createAddress(CreateAddressRequest createAddressRequest) {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        Address address = createAddressRequest.toEntityAddress(member);

        if (addressRepository.countByMember(member) == 0) {
            address.updateDefault(true);
        } else if (addressRepository.existsByMemberAndAddressAndAddressDetailAndZipcodeAndRecipientNameAndRecipientPhone(
                member,
                createAddressRequest.getAddress(),
                createAddressRequest.getAddressDetail(),
                createAddressRequest.getZipcode(),
                createAddressRequest.getRecipientName(),
                createAddressRequest.getRecipientPhone()
        )) {
            throw new AddressException(ResponseCode.ADDRESS_ALREADY_EXIST);
        }

        addressRepository.save(address);
    }

    // TODO: DB 적용 후 삭제
    public void testCreateAddress(Long memberId, CreateAddressRequest createAddressRequest) {
        Member member = validationService.validateMemberById(memberId);
        addressRepository.save(createAddressRequest.toEntityAddress(member));
    }

    public List<AddressDto> getAddressList() {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        return addressRepository.getAddressList(member);
    }

}