package com.shop.sudal.domain.user.service;

import com.shop.sudal.domain.entity.User;
import com.shop.sudal.domain.user.model.JoinRequest;
import com.shop.sudal.domain.user.model.JwtDto;
import com.shop.sudal.domain.user.model.LoginRequest;
import com.shop.sudal.domain.user.model.LoginDto;
import com.shop.sudal.domain.user.respository.UserRepository;
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
public class UserService {

    private final CommonValidationService validationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse<LoginDto> loginUser(LoginRequest loginRequest) {
        User user = validationService.validateUserByEmail(loginRequest.getEmail(),
                "User not found with email - " +  loginRequest.getEmail());
        if (validateUserByPassword(user.getPassword(), loginRequest.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            return new LoginResponse<>(true, new LoginDto(user), new JwtDto(accessToken, refreshToken));
        } else {
            return new LoginResponse<>(false, null, null);
        }
    }

    private boolean validateUserByPassword(String savedPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, savedPassword);
    }

    public ValidatedResponse<String> joinUser(JoinRequest joinRequest) {
        existUserByEmail(joinRequest.getEmail());

        joinRequest.encodePassword(passwordEncoder.encode(joinRequest.getPassword()));
        userRepository.save(joinRequest.toJoinUser());
        return new ValidatedResponse<>(true, "회원가입이 완료되었습니다.");
    }

    private void existUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new RuntimeException("Email already exists");
    }
}
