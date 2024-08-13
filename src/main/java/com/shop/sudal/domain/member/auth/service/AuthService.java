package com.shop.sudal.domain.member.auth.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberToken;
import com.shop.sudal.domain.member.auth.model.MemberTokenDto;
import com.shop.sudal.domain.member.auth.model.TokenDto;
import com.shop.sudal.domain.member.auth.repository.AuthRepository;
import com.shop.sudal.domain.member.member.model.LoginMemberRequest;
import com.shop.sudal.domain.member.member.model.LoginMemberResponse;
import com.shop.sudal.domain.member.member.model.MemberDto;
import com.shop.sudal.global.common.exception.AuthException;
import com.shop.sudal.global.common.exception.MemberException;
import com.shop.sudal.global.common.response.ResponseCode;
import com.shop.sudal.global.common.ValidationService;
import com.shop.sudal.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class AuthService {
    private final ValidationService validationService;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginMemberResponse login(LoginMemberRequest loginMemberRequest) {
        Member member = validationService.validateMemberByEmail(loginMemberRequest.getEmail());

        if (validateMemberByPassword(member.getPassword(), loginMemberRequest.getPassword())) {
            String accessToken = jwtUtil.createAccessToken(member);
            String refreshToken = jwtUtil.createRefreshToken(member);

            MemberToken memberToken = authRepository.findByMember(member)
                    .orElseGet(() -> new MemberTokenDto(member, refreshToken).toEntityMemberToken());
            memberToken.updateRefreshToken(refreshToken);
            authRepository.save(memberToken);

            return new LoginMemberResponse(new MemberDto(member), new TokenDto(accessToken, refreshToken));
        } else {
            throw new MemberException(ResponseCode.MEMBER_PASSWORD_INVALID);
        }
    }

    private boolean validateMemberByPassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public TokenDto refreshAccessToken(TokenDto tokenDto) {
        String accessToken = tokenDto.getAccessToken();
        String refreshToken = tokenDto.getRefreshToken();
        if (jwtUtil.validateToken(accessToken)) {
            Long memberId = jwtUtil.getMemberIdFromToken(accessToken);
            Member member = validationService.validateMemberById(memberId);
            MemberToken memberToken = authRepository.findByMember(member)
                    .orElseThrow(() -> new AuthException(ResponseCode.TOKEN_NOT_FOUND));

            if (memberToken.getRefreshToken().equals(refreshToken)) {
                String newAccessToken = jwtUtil.createAccessToken(member);
                return new TokenDto(newAccessToken, refreshToken);
            } else {
                throw new AuthException(ResponseCode.TOKEN_INVALID);
            }
        } else {
            throw new AuthException(ResponseCode.TOKEN_EXPIRED);
        }
    }
}
