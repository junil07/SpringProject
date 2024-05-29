package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Admin;
import com.example.demo.admin.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImple implements AdminService {

    private AdminRepository adminRepository;
    private HttpSession httpSession;
    private PasswordEncoder passwordEncoder;

    public AdminServiceImple(AdminRepository adminRepository, HttpSession httpSession,
                             PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.httpSession = httpSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(String adminId, String adminPassword) {
        Optional<Admin> admin = adminRepository.findByadminId(adminId);
        if (admin.isPresent()) { // isPresent() boolean 타입 값 있다면 true 아니면 false
            Admin admin1 = admin.get();
            if (admin1.getAdminPassword().equals(adminPassword)) {
                // 세션 저장
                httpSession.setAttribute("adminSession", admin1.getAdminId());
                httpSession.setAttribute("adminName", admin1.getAdminName());
                // System.out.println("세션이 저장되었다 " + httpSession.getAttribute("adminSession"));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        System.out.println((String) httpSession.getAttribute("adminSession"));
        return httpSession.getAttribute("adminSession") != null;
    }

    @Override
    public String findName(String adminId) {
        Admin admin = adminRepository.findByadminId(adminId).orElse(null);
        if (admin != null) {
            return admin.getAdminName();
        }
        return "관리자 아이디 없음 오류";
    }

    // 암호화 용도(평소에는 쓰지않음)
    public boolean authenticate(String username, String rawPassword) {
        Admin admin = adminRepository.findByadminId(username).orElseThrow(() -> new UsernameNotFoundException("없음"));
        if (admin != null) {
            // 기존 비밀번호 형식 확인 및 변환
            System.out.println("첫 번째 통과" + rawPassword + admin.getAdminPassword());
//            if (passwordEncoder.matches(rawPassword, admin.getAdminPassword())) {
                // 비밀번호가 평문이거나 다른 형식이라면 다시 인코딩
                System.out.println("두 번째 통과 " + rawPassword + admin.getAdminPassword());
                // if (!admin.getAdminPassword().startsWith("$2a$")) {
                    System.out.println("세 번째 통과");
                    admin.setAdminPassword(passwordEncoder.encode(rawPassword));
                    adminRepository.save(admin);
                // }
                return true;
//            }
        }
        return false;
    }



}
