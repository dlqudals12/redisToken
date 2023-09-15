package com.practiceProject.redis;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class RefreshToken {

    private String userKey;

    @Id
    private String refreshToken;

    public RefreshToken( String refreshToken, String userKey) {
        this.userKey = userKey;
        this.refreshToken = refreshToken;
    }
}
