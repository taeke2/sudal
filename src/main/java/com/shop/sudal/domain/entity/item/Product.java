package com.shop.sudal.domain.entity.item;

import com.shop.sudal.domain.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Product")
public class Product extends Item {
    // TODO: 완제품 정보 컬럼 정하기
}
