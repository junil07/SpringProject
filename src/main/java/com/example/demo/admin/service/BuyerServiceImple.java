package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.repository.BuyerRepository1;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BuyerServiceImple implements BuyerService {

    private BuyerRepository1 buyerRepository1;
    private PasswordEncoder passwordEncoder;

    public BuyerServiceImple(BuyerRepository1 buyerRepository1, PasswordEncoder passwordEncoder) {
        this.buyerRepository1 = buyerRepository1;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List getBuyerList() {
        List<Buyer> buyerList = buyerRepository1.findAll();
        return buyerList;
    }

    @Transactional // 메소드에 정의 되어있는 트랜잭션을 실행하고 닫음
    @Override
    public List<String> updateGrade(String buyerId, short grade) {

        List<String> flag = new ArrayList<>();
        String[] ids = buyerId.split("/");

        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i].trim();
        }

        for (String idpart : ids) {
            Buyer buyer = buyerRepository1.findById(idpart).orElse(null);

            if (buyer != null) {
                System.out.println("null 이 아니다");
                buyer.setBuyerGrade(grade);
                flag.add("1");
            } else {
                System.out.println("null 입니다");
                flag.add("0");
            }
        }

        return flag;

    }

    @Transactional
    @Override
    public List<String> updateActivation(String buyerId, short activation) {

        List<String> flag = new ArrayList<>();
        String[] ids = buyerId.split("/");

        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i].trim();
        }

        for (String idpart : ids) {
            Buyer buyer = buyerRepository1.findById(idpart).orElse(null);

            if (buyer != null) {
                System.out.println("활성화 null 이 아님");
                buyer.setBuyerActivation(activation);
                flag.add("1");
            } else {
                System.out.println("활성화 null 임");
                flag.add("0");
            }
        }

        return flag;

    }

    @Transactional
    @Override
    public boolean buyerNameUpdate(String buyerId, String buyerName) {
        boolean flag = false;
        Buyer buyer = buyerRepository1.findById(buyerId).orElse(null);

        if (buyer != null) {
            buyer.setBuyerName(buyerName);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean buyerBirthUpdate(String buyerId, String buyerBirth) {
        boolean flag = false;
        Buyer buyer = buyerRepository1.findById(buyerId).orElse(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(buyerBirth);
        } catch (ParseException e) {
            e.printStackTrace();;
        }

        if (buyer != null) {
            buyer.setBuyerBirth(date);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean buyerEmailUpdate(String buyerId, String buyerEmail) {
        boolean flag = false;
        Buyer buyer = buyerRepository1.findById(buyerId).orElse(null);

        if (buyer != null) {
            buyer.setBuyerEmail(buyerEmail);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean buyerAddrUpdate(String buyerId, String buyerAddr) {
        boolean flag = false;
        Buyer buyer = buyerRepository1.findById(buyerId).orElse(null);

        if (buyer != null) {
            buyer.setBuyerAddress(buyerAddr);
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public boolean buyerPhoneUpdate(String buyerId, String buyerPhone) {
        boolean flag = false;
        Buyer buyer = buyerRepository1.findById(buyerId).orElse(null);

        if (buyer != null) {
            buyer.setBuyerPhoneNum(buyerPhone);
            flag = true;
        }

        return flag;
    }

    public boolean authenticate(String username, String rawPassword) {
        Buyer buyer = buyerRepository1.findBybuyerId(username).orElseThrow(() -> new UsernameNotFoundException("없음"));
        if (buyer != null) {
            buyer.setBuyerPassword(passwordEncoder.encode(rawPassword));
            buyerRepository1.save(buyer);
        }
        return true;
    }

}
