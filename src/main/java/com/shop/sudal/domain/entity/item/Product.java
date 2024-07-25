package com.shop.sudal.domain.entity.item;

import com.shop.sudal.domain.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Product")
public class Product extends Item {
}
