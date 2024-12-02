package com.stackoverflow.security;

import com.stackoverflow.model.User;
import com.stackoverflow.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Get authenticated user's email
        String email = authentication.getName();

        // Fetch user ID from the database
        User user = userService.getUserByEmail(email);
        Long userId = user.getId();

        // Redirect to /profile/{userId}
        response.sendRedirect("/users/profile/" + userId);
    }
}
