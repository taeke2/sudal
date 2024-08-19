package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public MemberToken(Long id, Member member, String refreshToken) {
        if (member == null) throw new IllegalArgumentException("Member cannot be null or empty");
        if (refreshToken == null || refreshToken.isEmpty())
            throw new IllegalArgumentException("refreshToken cannot be null or empty");

        this.id = id;
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
