package com.expense.expensemanager.security;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private static UserService userService;

    @Autowired
    public SecurityUtils(UserService userService) {
        SecurityUtils.userService = userService;
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal() == null
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUser();
        }

        if (principal instanceof OAuth2User oauth2User && userService != null) {
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");
            String googleId = oauth2User.getAttribute("sub");
            String picture = oauth2User.getAttribute("picture");

            if (email != null) {
                if (googleId != null) {
                    return userService.getOrCreateUserFromOAuth(email, name, googleId, picture);
                }

                return userService.findByEmail(email).orElse(null);
            }
        }

        return null;
    }
}

