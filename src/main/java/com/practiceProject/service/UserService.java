package com.practiceProject.service;

import com.practiceProject.dto.request.user.LoginUser;
import com.practiceProject.dto.request.user.SaveUser;
import com.practiceProject.entity.User;
import com.practiceProject.exception.NoMatchesException;
import com.practiceProject.exception.NoneException;
import com.practiceProject.redis.RefreshToken;
import com.practiceProject.repository.RefreshTokenRepository;
import com.practiceProject.repository.UserRepository;
import com.practiceProject.security.JwtTokenProvider;
import com.practiceProject.security.model.CustomDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveUser(SaveUser saveUser) {

        User user = new User(saveUser);

        userRepository.save(user);
    }

    @Transactional
    public CustomDetails loginUser(LoginUser loginUser, String ipValue) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findUserByLoginId(loginUser.getLoginId()).orElseThrow(() -> new NoneException("유저가"));

        if(!encoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new NoMatchesException("비밀번호가");
        }

        CustomDetails customDetails = new CustomDetails(user);
        refreshTokenRepository.save(new RefreshToken(JwtTokenProvider.generateToken(customDetails, JwtTokenProvider.TokenType.REFRESH), ipValue + "-" + user.getIdx()));

        return customDetails;
    }

    @Transactional
    public Boolean logoutUser(String key) {
       return refreshTokenRepository.deleteToken(key);
    }

}
