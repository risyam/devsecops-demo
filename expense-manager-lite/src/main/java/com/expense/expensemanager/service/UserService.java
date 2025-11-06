package com.expense.expensemanager.service;

import com.expense.expensemanager.model.Role;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    public User createUser(String email, String name, String googleId, String pictureUrl, Role role) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setGoogleId(googleId);
        user.setPictureUrl(pictureUrl);
        user.setRole(Role.USER); // All users have USER role
        user.setActive(true);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void updateLastLogin(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deactivateUser(UUID userId) {
        User user = getUserById(userId);
        user.setActive(false);
        userRepository.save(user);
    }

    public void activateUser(UUID userId) {
        User user = getUserById(userId);
        user.setActive(true);
        userRepository.save(user);
    }

    public User getOrCreateUserFromOAuth(String email, String name, String googleId, String pictureUrl) {
        Optional<User> existingUser = findByGoogleId(googleId);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update user info in case it changed
            user.setName(name);
            user.setPictureUrl(pictureUrl);
            updateLastLogin(user.getId());
            return updateUser(user);
        }
        
        // Check if user exists by email
        existingUser = findByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setGoogleId(googleId);
            user.setName(name);
            user.setPictureUrl(pictureUrl);
            updateLastLogin(user.getId());
            return updateUser(user);
        }
        
        // Create new user with USER role
        return createUser(email, name, googleId, pictureUrl, Role.USER);
    }
}

