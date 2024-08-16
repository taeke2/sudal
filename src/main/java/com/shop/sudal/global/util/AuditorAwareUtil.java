package com.shop.sudal.global.util;

import com.shop.sudal.domain.member.auth.model.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuditorAwareUtil implements AuditorAware<Long> {

    @Override
    public @NonNull Optional<Long> getCurrentAuditor() {        // 함수형 스타일 코드

        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof MemberDetails)
                .map(principle -> ((MemberDetails) principle).getAuthMemberDto().getId());
    }

//    @Override
//    public @NonNull Optional<Long> getCurrentAuditor() {       // 명령형 스타일 코드
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
//            return Optional.empty();
//        }
//
//        // Assuming that the principal is of type Member and contains the ID
//        MemberDetails member = (MemberDetails) authentication.getPrincipal();
//        return Optional.ofNullable(member.getAuthMemberDto().getId());
//    }
}
