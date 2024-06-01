package com.example.demo.admin.security;

import com.example.demo.admin.Entity.Admin;
import com.example.demo.admin.repository.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImple implements MyUserDetailsService{

    private AdminRepository adminRepository;

    public MyUserDetailsServiceImple(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 첫 번째 호출");
        System.out.println("username : " + username);
        Admin admin = adminRepository.findByadminId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        System.out.println("loadUserByUsername 두 번째 호출");
        return User.builder().username(admin.getAdminId()).password(admin.getAdminPassword()).roles(Role.ADMIN.name()).build();
    }
}
