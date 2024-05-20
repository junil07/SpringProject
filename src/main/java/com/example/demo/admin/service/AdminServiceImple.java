package com.example.demo.admin.service;

import com.example.project.admin.Entity.Admin;
import com.example.project.admin.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImple implements AdminService {

    private AdminRepository adminRepository;
    private HttpSession httpSession;

    public AdminServiceImple(AdminRepository adminRepository, HttpSession httpSession) {
        this.adminRepository = adminRepository;
        this.httpSession = httpSession;
    }

    @Override
    public boolean login(String adminId, String adminPassword) {
        Optional<Admin> admin = adminRepository.findByadminId(adminId);
        if (admin.isPresent()) { // isPresent() boolean 타입 값 있다면 true 아니면 false
            Admin admin1 = admin.get();
            if (admin1.getAdminPassword().equals(adminPassword)) {
                // 세션 저장
                httpSession.setAttribute("adminSession", admin1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public boolean isLoggedIn() {
        return httpSession.getAttribute("adminSession") != null;
    }

}
