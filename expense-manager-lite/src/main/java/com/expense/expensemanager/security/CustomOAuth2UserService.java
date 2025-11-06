package com.expense.expensemanager.security;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Extract user info from Google OAuth2
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String googleId = oauth2User.getAttribute("sub");
        String pictureUrl = oauth2User.getAttribute("picture");

        // Get or create user
        User user = userService.getOrCreateUserFromOAuth(email, name, googleId, pictureUrl);

        return new CustomUserDetails(user, oauth2User.getAttributes());
    }
}

