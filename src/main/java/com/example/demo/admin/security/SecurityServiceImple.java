package com.example.demo.admin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/*
    현재 세션에 유지되고 있는 User 객체가 파라미터 role에 해당하는 권한을 가지고 있는가?
    있으면 - true 없으면 - false 반환
 */

@Service
public class SecurityServiceImple implements SecurityService{

    @Override
    public boolean hasRole(User user, String role) {
        return user.getAuthorities().stream().anyMatch(authority ->
                role.equals(authority.getAuthority()));
    }
}
