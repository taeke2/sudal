package com.shop.sudal.domain.user.service;

import com.shop.sudal.domain.entity.User;
import com.shop.sudal.domain.user.dto.JoinRequest;
import com.shop.sudal.domain.user.dto.JwtResponse;
import com.shop.sudal.domain.user.dto.LoginRequest;
import com.shop.sudal.domain.user.dto.LoginResponse;
import com.shop.sudal.domain.user.respository.UserRepository;
import com.shop.sudal.global.common.LoginResult;
import com.shop.sudal.global.common.ValidatedResult;
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

    public LoginResult<LoginResponse> loginUser(LoginRequest loginRequest) {
        User user = validateEmail(loginRequest.getEmail());
        if (validatePassword(user.getPassword(), loginRequest.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            return new LoginResult<>(true, new LoginResponse(user), new JwtResponse(accessToken, refreshToken));
        } else {
            return new LoginResult<>(false, null, null);
        }
    }

    private User validateEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    private boolean validatePassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public ValidatedResult<String> joinUser(JoinRequest joinRequest) {
        if (userRepository.findByEmail(joinRequest.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");

        joinRequest.encodePassword(passwordEncoder.encode(joinRequest.getPassword()));
        User saved = userRepository.save(joinRequest.toJoinUser());
        return new ValidatedResult<>(true, "회원가입이 완료되었습니다. user Id = " + saved.getId());
    }
}
