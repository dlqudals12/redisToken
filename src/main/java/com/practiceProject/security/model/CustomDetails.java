package com.practiceProject.security.model;


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
}
