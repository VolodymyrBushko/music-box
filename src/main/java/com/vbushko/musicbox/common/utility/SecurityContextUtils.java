package com.vbushko.musicbox.common.utility;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtils {

    public String getUsername() {
        return getUser().getUsername();
    }

    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
