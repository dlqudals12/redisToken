package com.practiceProject.controller;

import com.practiceProject.dto.response.DefaultResponse;
import com.practiceProject.security.model.CustomDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check/api")
public class CheckController {

    @GetMapping("/check_user")
    public DefaultResponse checkUser(Authentication authentication) {
        CustomDetails details = (CustomDetails) authentication.getDetails();

        return new DefaultResponse(details);
    }

}
