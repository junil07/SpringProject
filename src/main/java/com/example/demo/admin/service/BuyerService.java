package com.example.demo.admin.service;

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

}
