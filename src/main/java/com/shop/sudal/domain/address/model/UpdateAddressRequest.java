package com.shop.sudal.domain.address.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateAddressRequest {
    private String address;
    private String addressDetail;
    private String recipientName;
    private String recipientPhone;
    private String zipcode;
    private String deliveryInstructions;
}
