package com.expense.expensemanager.security;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        User user = extractUser(authentication);
        if (user != null) {
            userService.updateLastLogin(user.getId());
        }

        setDefaultTargetUrl("/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private User extractUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUser();
        }

        if (principal instanceof OAuth2User oauth2User) {
            String email = oauth2User.getAttribute("email");
            return (email != null) ? userService.findByEmail(email).orElse(null) : null;
        }

        return null;
    }
}

