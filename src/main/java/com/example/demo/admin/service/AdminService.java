package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Admin;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface AdminService {

    public boolean login(String adminId, String adminPassword);

    public void logout();

    public boolean isLoggedIn();

}
