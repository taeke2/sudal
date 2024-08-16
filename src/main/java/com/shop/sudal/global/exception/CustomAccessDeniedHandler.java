package com.shop.sudal.global.exception;

import com.shop.sudal.global.response.ResponseCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ResponseCode responseCode = ResponseCode.ACCESS_FORBIDDEN;
        response.setStatus(responseCode.getHttpStatusCode());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{ \"code\": " + responseCode.getHttpStatusCode() +
                ", \"isSuccess\": " + responseCode.getIsSuccess() +
                ", \"error\": \"" + responseCode.getMessage() + "\"}");
    }
}
