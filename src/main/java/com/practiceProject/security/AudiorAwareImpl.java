package com.practiceProject.security;


import com.practiceProject.security.model.CustomDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AudiorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }

        CustomDetails details = (CustomDetails) authentication.getDetails();
        return Optional.of(details.getLoginId());
    }
}
