package com.practiceProject.security.model;


import com.practiceProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CustomDetails {

    private Long idx;
    private String loginId;
    private String name;

    public CustomDetails(User user) {
        this.idx = user.getIdx();
        this.loginId = user.getLoginId();
        this.name = user.getName();
    }
}
