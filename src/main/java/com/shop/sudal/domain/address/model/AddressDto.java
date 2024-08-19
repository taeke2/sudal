package com.shop.sudal.domain.address.model;

import com.shop.sudal.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String address;
    private String addressDetail;
    private String recipientName;
    private String recipientPhone;
    private String zipcode;
    private String deliveryInstructions;
    private Boolean isDefault;

    public AddressDto(Address address) {
        this.address = address.getAddress();
        this.addressDetail = address.getAddressDetail();
        this.recipientName = address.getRecipientName();
        this.recipientPhone = address.getRecipientPhone();
        this.zipcode = address.getZipcode();
        this.deliveryInstructions = address.getDeliveryInstructions();
        this.isDefault = address.getIsDefault();
    }
}
