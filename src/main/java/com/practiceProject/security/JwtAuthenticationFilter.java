package com.practiceProject.security;



import com.practiceProject.entity.User;
import com.practiceProject.exception.CommonException;
import com.practiceProject.exception.NoneException;
import com.practiceProject.exception.RefreshException;
import com.practiceProject.exception.UserException;
import com.practiceProject.redis.RefreshToken;
import com.practiceProject.repository.RefreshTokenRepository;
import com.practiceProject.repository.UserRepository;
import com.practiceProject.security.model.CustomDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 토큰 검사할 url 목록
    private final String[] urls = {
            "/check/api/check_user",
            "/user/api/logout_user"
    };

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (checkTokenPass(url)) {
            try {
                String jwt = JwtTokenProvider.getJwtFromRequest(request); // request에서 jwt 토큰을 꺼낸다.

                if(jwt == null) {
                    response.sendError(400, new UserException("이미 삭제되거나 없는").getMsg());
                    return;
                }

                Long userIdx = Long.parseLong(JwtTokenProvider.getValueFromJWT(jwt, "idx") + ""); // jwt에서 사용자 idx를
                User user = userRepository.findUserByIdx(userIdx);

                if (user == null) {
                    response.sendError(400, new UserException("이미 삭제되거나 없는").getMsg());
                    return;
                }

                String ipValue = "";
                Cookie[] cookies = request.getCookies();

                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("userIp")) {
                        ipValue = cookie.getValue();
                    }
                }

                if(refreshTokenRepository.findById(ipValue + "-" + user.getIdx())) {
                    response.sendError(400, new UserException("이미 삭제되거나 없는").getMsg());
                }

                if (ObjectUtils.isEmpty(jwt) || !JwtTokenProvider.validateToken(jwt)) {
                    Cookie cookie = new Cookie("accessToken", JwtTokenProvider.generateToken(userIdx, JwtTokenProvider.TokenType.ACCESS));
                    cookie.setMaxAge(60*60*24);
                    cookie.setPath("/");

                    response.addCookie(cookie);
                }

                saveCustomDetails(user);
            } catch (Exception ex) {
                logger.error("Could not set user authentication in security context", ex);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkTokenPass(String url) {
        boolean result = false;
        for (int i = 0; i < urls.length; i++)
            result = result || url.contains(urls[i]);

        return result;
    }

    private void saveCustomDetails(User user) {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserAuthentication authentication = new UserAuthentication(user.getLoginId(), null, auth); // id를 인증한다.
        CustomDetails userDetails = new CustomDetails(user.getIdx(), user.getLoginId(), user.getName());
        authentication.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}