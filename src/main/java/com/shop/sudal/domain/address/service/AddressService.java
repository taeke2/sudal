package com.shop.sudal.domain.address.service;

import com.shop.sudal.domain.address.model.AddressDto;
import com.shop.sudal.domain.address.model.CreateAddressRequest;
import com.shop.sudal.domain.address.model.UpdateAddressRequest;
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

    @Transactional(readOnly = true)
    public List<AddressDto> getAddressList() {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);
        return addressRepository.getAddressList(member);
    }

    public void updateAddress(Long id, UpdateAddressRequest updateAddressRequest) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressException(ResponseCode.ADDRESS_NOT_FOUND));

        address.updateAddress(updateAddressRequest);
    }

    public void updateDefaultAddress(Long id) {
        Long memberId = validationService.validateMemberIdByAuth();
        Member member = validationService.validateMemberById(memberId);

        Address defaultAddress = addressRepository.findByMemberAndIsDefault(member, true)
                .orElseThrow(() -> new AddressException(ResponseCode.ADDRESS_NOT_FOUND));
        defaultAddress.updateDefault(false);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressException(ResponseCode.ADDRESS_NOT_FOUND));
        address.updateDefault(true);
    }

    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressException(ResponseCode.ADDRESS_NOT_FOUND));

        addressRepository.delete(address);
    }

}