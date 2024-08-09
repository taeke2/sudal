package com.shop.sudal.domain.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberToken;
import com.shop.sudal.domain.member.model.*;
import com.shop.sudal.domain.member.repository.MemberRepository;
import com.shop.sudal.domain.member.repository.MemberTokenRepository;
import com.shop.sudal.global.common.response.ResponseCode;
import com.shop.sudal.global.common.exception.MemberException;
import com.shop.sudal.domain.member.model.LoginMemberResponse;
import com.shop.sudal.global.common.exception.TokenException;
import com.shop.sudal.global.common.service.CommonValidationService;
import com.shop.sudal.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MemberService {

    private final CommonValidationService validationService;
    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginMemberResponse login(LoginMemberRequest loginMemberRequest) {
        Member member = validationService.validateMemberByEmail(loginMemberRequest.getEmail());

        if (validateMemberByPassword(member.getPassword(), loginMemberRequest.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(member.getId());
            String refreshToken = jwtUtil.generateRefreshToken(member.getId());

            MemberToken memberToken = memberTokenRepository.findByMember(member)
                    .orElseGet(() -> new MemberTokenDto(member, refreshToken).toEntityMemberToken());
            memberToken.updateRefreshToken(refreshToken);
            memberTokenRepository.save(memberToken);

            return new LoginMemberResponse(new MemberDto(member), new TokenDto(accessToken, refreshToken));
        } else {
            throw new MemberException(ResponseCode.MEMBER_PASSWORD_INVALID);
        }
    }

    private boolean validateMemberByPassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public Void join(CreateMemberRequest createMemberRequest) {
        if (memberRepository.existsMemberByEmail(createMemberRequest.getEmail())) {
            throw new MemberException(ResponseCode.MEMBER_ALREADY_EXIST);
        } else {
            Member member = createMemberRequest.toEntityMember();
            member.encodePassword(passwordEncoder.encode(createMemberRequest.getPassword()));
            memberRepository.save(member);
        }
        return null;
    }

    public TokenDto refreshAccessToken(TokenDto tokenDto) {
        String refreshToken = tokenDto.getRefreshToken();
        if (jwtUtil.validateToken(refreshToken)) {
            Long memberId = jwtUtil.getMemberIdFromToken(refreshToken);
            Member member = validationService.validateMemberById(memberId);
            MemberToken memberToken = memberTokenRepository.findByMember(member)
                    .orElseThrow(() -> new TokenException(ResponseCode.TOKEN_NOT_FOUND));

            if (memberToken.getRefreshToken().equals(refreshToken)) {
                String newAccessToken = jwtUtil.generateAccessToken(memberId);
                return new TokenDto(newAccessToken, refreshToken);
            } else {
                throw new TokenException(ResponseCode.TOKEN_INVALID);
            }
        } else {
            throw new TokenException(ResponseCode.TOKEN_INVALID);
        }
    }
}
