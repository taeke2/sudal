package com.shop.sudal.domain.address.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.sudal.domain.address.model.AddressDto;
import com.shop.sudal.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.shop.sudal.domain.entity.QAddress.address1;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AddressRepositoryImpl implements AddressRepositoryQdsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AddressDto> getAddressList(Member member) {
        return queryFactory.select(Projections.constructor(AddressDto.class, address1))
                .from(address1)
                .where(address1.member.eq(member))
                .orderBy(address1.isDefault.desc(), address1.updatedAt.desc())
                .fetch();
    }
}
