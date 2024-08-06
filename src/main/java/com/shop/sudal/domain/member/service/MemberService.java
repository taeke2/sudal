package com.shop.sudal.domain.member.service;

import com.shop.sudal.domain.entity.Member;
import com.shop.sudal.domain.entity.MemberToken;
import com.shop.sudal.domain.member.model.JoinRequest;
import com.shop.sudal.domain.member.model.JwtDto;
import com.shop.sudal.domain.member.model.LoginRequest;
import com.shop.sudal.domain.member.model.LoginDto;
import com.shop.sudal.domain.member.respository.MemberRepository;
import com.shop.sudal.domain.member.respository.MemberTokenRepository;
import com.shop.sudal.global.common.response.LoginResponse;
import com.shop.sudal.global.common.response.ValidatedResponse;
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

    public LoginResponse<LoginDto> login(LoginRequest loginRequest) {
        Member member = validationService.validateMemberByEmail(loginRequest.getEmail(),
                "Member not found with email - " + loginRequest.getEmail());
        if (validateMemberByPassword(member.getPassword(), loginRequest.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(member.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(member.getEmail());

            MemberToken memberToken = memberTokenRepository.findByMember(member)
                    .orElse(new MemberToken(member, refreshToken));
            memberToken.updateRefreshToken(refreshToken);
            memberTokenRepository.save(memberToken);

            return new LoginResponse<>(true, new LoginDto(member), new JwtDto(accessToken, refreshToken));
        } else {
            return new LoginResponse<>(false, null, null);
        }
    }

    private boolean validateMemberByPassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public ValidatedResponse<String> join(JoinRequest joinRequest) {
        existMemberByEmail(joinRequest.getEmail());

        joinRequest.encodePassword(passwordEncoder.encode(joinRequest.getPassword()));
        memberRepository.save(joinRequest.toEntityMember());
        return new ValidatedResponse<>(true, "회원가입이 완료되었습니다.");
    }

    private void existMemberByEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent())
            throw new RuntimeException("Email already exists");
    }

    public JwtDto refreshAccessToken(String refreshToken) {
        if (jwtUtil.validateToken(refreshToken)) {
            String memberId = jwtUtil.getMemberIdFromToken(refreshToken);
            Member member = validationService.validateMemberByEmail(memberId, "Invalid member");
            MemberToken memberToken = memberTokenRepository.findByMember(member)
                    .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

            if (memberToken.getRefreshToken().equals(refreshToken)) {
                String newAccessToken = jwtUtil.generateAccessToken(memberId);
                return new JwtDto(newAccessToken, refreshToken);
            } else {
                throw new RuntimeException("Invalid refresh token");
            }
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}
