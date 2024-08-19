package com.shop.sudal.domain.address.repository;

import com.shop.sudal.domain.entity.Address;
import com.shop.sudal.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryQdsl {
    Boolean existsByMemberAndAddressAndAddressDetailAndZipcodeAndRecipientNameAndRecipientPhone(
            Member member,
            String address,
            String addressDetail,
            String zipcode,
            String recipientName,
            String recipientPhone
    );

    Long countByMemberAndIsDefault(Member member, Boolean isDefault);

    Long countByMember(Member member);
}
