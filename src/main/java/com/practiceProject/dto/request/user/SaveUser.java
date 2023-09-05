package com.practiceProject.dto.request.user;

import lombok.Data;

@Data
public class SaveUser {

    private String loginId;
    private String password;
    private String name;
}
