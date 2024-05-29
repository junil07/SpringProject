package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.repository.SellerRepository1;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImple implements SellerService {

    private SellerRepository1 sellerRepository1;

    public SellerServiceImple(SellerRepository1 sellerRepository1) {
        this.sellerRepository1 = sellerRepository1;
    }

    @Override
    public List getSellerList() {
        List<Seller> sellerList = sellerRepository1.findAll();
        return sellerList;
    }

    @Transactional
    @Override
    public List<String> updateActivation(String sellerId, short activation) {

        List<String> flag = new ArrayList<>();
        String[] ids = sellerId.split("/");

        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i].trim();
        }

        for (String idpart : ids) {
            Seller seller = sellerRepository1.findById(idpart).orElse(null);

            if (seller != null) {
                seller.setSellerActivation(activation);
                flag.add("1");
            } else {
                flag.add("0");
            }
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerNameUpdate(String sellerId, String sellerName) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerName(sellerName);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerBirthUpdate(String sellerId, String sellerBirth) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(sellerBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (seller != null) {
            seller.setSellerBirth(date);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerEmailUpdate(String sellerId, String sellerEmail) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerEmail(sellerEmail);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerAddrUpdate(String sellerId, String sellerAddress) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerAddress(sellerAddress);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerPhoneUpdate(String sellerId, String sellerPhone) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerPhoneNum(sellerPhone);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean sellerBNumUpdate(String sellerId, String sellerBNum) {
        boolean flag = false;
        Seller seller = sellerRepository1.findById(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerBnum(sellerBNum);
            flag = true;
        }

        return flag;
    }
}
