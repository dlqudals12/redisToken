package com.practiceProject.controller;

import com.practiceProject.dto.request.user.LoginUser;
import com.practiceProject.dto.request.user.SaveUser;
import com.practiceProject.dto.response.DefaultResponse;
import com.practiceProject.exception.NoMatchesException;
import com.practiceProject.exception.NoneException;
import com.practiceProject.exception.RefreshException;
import com.practiceProject.exception.UserException;
import com.practiceProject.security.JwtTokenProvider;
import com.practiceProject.security.model.CustomDetails;
import com.practiceProject.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/logout_user")
    public DefaultResponse logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
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

        CustomDetails details = (CustomDetails) authentication.getDetails();

        Boolean delete = userService.logoutUser(ipValue + "-" + details.getIdx());

        if(!delete) {
            throw new RefreshException();
        }

        Cookie accessToken = new Cookie("accessToken", null);
        accessToken.setMaxAge(0);
        accessToken.setPath("/");

        response.addCookie(accessToken);

        return new DefaultResponse();
    }
}
