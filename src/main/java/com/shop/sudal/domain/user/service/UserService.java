package com.shop.sudal.domain.user.service;

import com.shop.sudal.domain.entity.User;
import com.shop.sudal.domain.user.dto.JoinRequest;
import com.shop.sudal.domain.user.dto.JwtDto;
import com.shop.sudal.domain.user.dto.LoginRequest;
import com.shop.sudal.domain.user.dto.LoginDto;
import com.shop.sudal.domain.user.respository.UserRepository;
import com.shop.sudal.global.common.LoginResponse;
import com.shop.sudal.global.common.ValidatedResponse;
import com.shop.sudal.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse<LoginDto> loginUser(LoginRequest loginRequest) {
        User user = validateEmail(loginRequest.getEmail());
        if (validatePassword(user.getPassword(), loginRequest.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            return new LoginResponse<>(true, new LoginDto(user), new JwtDto(accessToken, refreshToken));
        } else {
            return new LoginResponse<>(false, null, null);
        }
    }

    private User validateEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    private boolean validatePassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public ValidatedResponse<String> joinUser(JoinRequest joinRequest) {
        existEmail(joinRequest.getEmail());

        joinRequest.encodePassword(passwordEncoder.encode(joinRequest.getPassword()));
        userRepository.save(joinRequest.toJoinUser());
        return new ValidatedResponse<>(true, "회원가입이 완료되었습니다.");
    }

    private void existEmail(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new RuntimeException("Email already exists");
    }
}
