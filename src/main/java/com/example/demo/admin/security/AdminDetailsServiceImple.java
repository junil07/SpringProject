package com.example.demo.admin.security;

import com.example.demo.admin.Entity.Admin;
import com.example.demo.admin.repository.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImple implements AdminDetailsService {

    private AdminRepository adminRepository;

    public AdminDetailsServiceImple(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByadminId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        return User.builder().username(admin.getAdminId()).password(admin.getAdminPassword()).roles(Role.ADMIN.name()).build();
    }
}
