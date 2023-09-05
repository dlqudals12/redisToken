package com.practiceProject.redis;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class RefreshToken {

    private Long userIdx;

    @Id
    private String refreshToken;

    public RefreshToken( String refreshToken, Long userIdx) {
        this.userIdx = userIdx;
        this.refreshToken = refreshToken;
    }
}
