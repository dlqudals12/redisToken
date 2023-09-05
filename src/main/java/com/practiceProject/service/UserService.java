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
    public Long loginUser(LoginUser loginUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findUserByLoginId(loginUser.getLoginId()).orElseThrow(() -> new NoneException("유저가"));

        if(!encoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new NoMatchesException("비밀번호가");
        }

        refreshTokenRepository.save(new RefreshToken(JwtTokenProvider.generateToken(user.getIdx(), JwtTokenProvider.TokenType.REFRESH), user.getIdx()));

        return user.getIdx();
    }

}
