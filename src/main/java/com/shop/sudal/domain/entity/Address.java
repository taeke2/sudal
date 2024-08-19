package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String recipientName;

    @Column(nullable = false)
    private String recipientPhone;

    @Column(nullable = false)
    private String zipcode;

    private String deliveryInstructions;

    @Column(nullable = false)
    private Boolean isDefault;

    public void updateDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Builder
    public Address(Long id, Member member, String address, String addressDetail, String recipientName, String recipientPhone,
                   String zipcode, String deliveryInstructions, Boolean isDefault) {
        if (address == null || address.isEmpty()) throw new IllegalArgumentException("Address cannot be null or empty");
        if (addressDetail == null || addressDetail.isEmpty())
            throw new IllegalArgumentException("Address detail cannot be null or empty");
        if (recipientName == null || recipientName.isEmpty())
            throw new IllegalArgumentException("Recipient name cannot be null or empty");
        if (recipientPhone == null || recipientPhone.isEmpty())
            throw new IllegalArgumentException("Recipient phone cannot be null or empty");
        if (zipcode == null || zipcode.isEmpty()) throw new IllegalArgumentException("Zipcode cannot be null or empty");

        this.id = id;
        this.member = member;
        this.address = address;
        this.addressDetail = addressDetail;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.zipcode = zipcode;
        this.deliveryInstructions = deliveryInstructions;
        this.isDefault = isDefault;
    }
}
