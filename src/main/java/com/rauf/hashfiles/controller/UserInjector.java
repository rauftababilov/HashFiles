package com.rauf.hashfiles.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.rauf.controller")
public class UserInjector {
    public static final String USER_ID = "userId";

    @ModelAttribute(USER_ID)
    public String injectUser(HttpServletRequest request) {
        String userId = request.getHeader("X-Auth-User");
        if (userId != null && userId.isEmpty()) {
            return null;
        }
        return userId;
    }
}
