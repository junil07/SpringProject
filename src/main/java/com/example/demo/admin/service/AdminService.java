package com.example.demo.admin.service;

public interface AdminService {

    public boolean login(String adminId, String adminPassword);
    public boolean isLoggedIn();
    public String findName(String adminId);

}
