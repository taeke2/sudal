package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roles_id")
    private Role role;

    @Builder
    public MemberRole(Long id, Member member, Role role) {
        if (member == null) throw new IllegalArgumentException("Member cannot be null");
        if (role == null) throw new IllegalArgumentException("Role cannot be null");

        this.id = id;
        this.member = member;
        this.role = role;
    }
}
