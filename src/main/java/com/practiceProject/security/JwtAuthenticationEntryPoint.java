package com.practiceProject.security;

import com.practiceProject.security.model.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException e) throws IOException, ServletException {

        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");

        request.setAttribute("response.failure.code", unAuthorizationCode.getName());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, unAuthorizationCode.getMessage());
    }
}