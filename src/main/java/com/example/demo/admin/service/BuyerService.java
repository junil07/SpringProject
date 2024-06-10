package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Buyer;

import java.util.List;

public interface BuyerService {

    public List getBuyerList();
    public List<String> updateGrade(String buyerId, short grade);
    public List<String> updateActivation(String buyerId, short activation);
    public boolean buyerNameUpdate(String buyerId, String buyerName);
    public boolean buyerBirthUpdate(String buyerId, String buyerBirth);
    public boolean buyerEmailUpdate(String buyerId, String buyerEmail);
    public boolean buyerAddrUpdate(String buyerId, String buyerAddr);
    public boolean buyerPhoneUpdate(String buyerId, String buyerPhone);
    public int idCheck(String buyerId);
    public Buyer register(Buyer buyer);
    public Buyer idFind(String buyerName, String buyerEmail);
    public Buyer buyerFind(String buyerId, String buyerEmail);
    public boolean buyerPwdUpdate(String buyerId, String buyerPwd);

}
