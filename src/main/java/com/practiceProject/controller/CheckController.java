package com.practiceProject.controller;

import com.practiceProject.dto.response.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check/api")
public class CheckController {

    @GetMapping("/check_user")
    public DefaultResponse checkUser() {
        return new DefaultResponse();
    }

}
