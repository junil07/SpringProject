package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Seller;

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
    public Seller register(Seller seller);
    public Seller idFind(String sellerName, String sellerEmail);
    public Seller sellerFind(String sellerId, String sellerEmail);
    public boolean sellerPwdUpdate(String sellerId, String sellerPwd);

}
