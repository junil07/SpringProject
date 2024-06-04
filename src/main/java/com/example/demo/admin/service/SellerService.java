package com.example.demo.admin.service;

import java.util.List;

public interface SellerService {

    public List getSellerList();
    public List<String> updateActivation(String sellerId, short activation);
    public boolean sellerNameUpdate(String sellerId, String sellerName);
    public boolean sellerBirthUpdate(String sellerId, String sellerBirth);
    public boolean sellerEmailUpdate(String sellerId, String sellerEmail);
    public boolean sellerAddrUpdate(String sellerId, String sellerAddress);
    public boolean sellerPhoneUpdate(String sellerId, String sellerPhone);
    public boolean sellerBNumUpdate(String sellerId, String sellerBNum);
    public int idCheck(String sellerId);

}
