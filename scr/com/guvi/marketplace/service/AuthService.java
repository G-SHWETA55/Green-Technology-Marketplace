// com/guvi/marketplace/service/AuthService.java
package com.guvi.marketplace.service;

import com.guvi.marketplace.dao.UserDAO;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.util.ValidationUtil;
import com.guvi.marketplace.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String email, String password) throws AppException {
        ValidationUtil.require(ValidationUtil.isEmail(email), "Invalid email");
        ValidationUtil.require(password != null && !password.isEmpty(), "Password required");

        User u = userDAO.findByEmail(email);
        String hash = hash(password);
        if (!hash.equals(u.getPasswordHash())) {
            throw new com.guvi.marketplace.exception.ValidationException("Invalid credentials");
        }
        return u;
    }

    public String hash(String input) throws AppException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new AppException("Error hashing", e);
        }
    }
}
