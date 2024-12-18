package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private Integer seq;

    @Column(nullable = false)
    private String url;

    @Builder
    public ItemImage(long id, Item item, Integer seq, String url) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        if (seq < 0) throw new IllegalArgumentException("seq must be greater than or equal to 0");
        if (url.isBlank() || url.isEmpty()) throw new IllegalArgumentException("url cannot be null or empty");

        this.id = id;
        this.item = item;
        this.seq = seq;
        this.url = url;
    }
}
