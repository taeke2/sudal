package com.shop.sudal.domain.address.model;

import com.shop.sudal.domain.entity.Address;
import com.shop.sudal.domain.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {

    @NotBlank(message = "주소가 입력되지 않았습니다")
    private String address;

    private String addressDetail;

    @NotBlank(message = "받는 사람이 입력되지 않았습니다")
    private String recipientName;

    @NotBlank(message = "받는 사람 연락처가 입력되지 않았습니다")
    private String recipientPhone;

    @NotBlank(message = "우편번호가 입력되지 않았습니다")
    private String zipcode;

    @NotNull
    private String deliveryInstructions;

    private Boolean isDefault;

    public Address toEntityAddress(Member member) {
        return Address.builder()
                .member(member)
                .address(address)
                .addressDetail(addressDetail)
                .recipientName(recipientName)
                .recipientPhone(recipientPhone)
                .zipcode(zipcode)
                .deliveryInstructions(deliveryInstructions)
                .isDefault(isDefault)
                .build();
    }
}
