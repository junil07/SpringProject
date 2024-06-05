package com.example.demo.admin.security;

import org.springframework.security.core.userdetails.User;

public interface SecurityService {
    public boolean hasRole(User user, String role);
}
