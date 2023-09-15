package com.practiceProject.controller;

import com.practiceProject.dto.request.user.LoginUser;
import com.practiceProject.dto.request.user.SaveUser;
import com.practiceProject.dto.response.DefaultResponse;
import com.practiceProject.exception.NoMatchesException;
import com.practiceProject.exception.UserException;
import com.practiceProject.security.JwtTokenProvider;
import com.practiceProject.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/save_user")
    public DefaultResponse saveUser(@RequestBody SaveUser saveUser) {

        userService.saveUser(saveUser);

        return new DefaultResponse();
    }

    @PostMapping("/login_user")
    public DefaultResponse loginUser(@RequestBody LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) {
        String ipValue = "";
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("userIp")) {
                ipValue = cookie.getValue();
            }
        }

        if(ipValue.isEmpty()) {
            throw new NoMatchesException("쿠키가");
        }

        Long idx = userService.loginUser(loginUser, ipValue);
        Cookie cookie = new Cookie("accessToken", JwtTokenProvider.generateToken(idx, JwtTokenProvider.TokenType.ACCESS));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");

        response.addCookie(cookie);

        return new DefaultResponse();
    }
}
