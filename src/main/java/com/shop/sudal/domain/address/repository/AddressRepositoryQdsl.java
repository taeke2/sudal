package com.shop.sudal.domain.address.repository;

import com.shop.sudal.domain.address.model.AddressDto;
import com.shop.sudal.domain.entity.Member;

import java.util.List;

public interface AddressRepositoryQdsl {
    List<AddressDto> getAddressList(Member member);
}
