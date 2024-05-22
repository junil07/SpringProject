package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.repository.BuyerRepository1;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerServiceImple implements BuyerService {

    private BuyerRepository1 buyerRepository1;

    public BuyerServiceImple(BuyerRepository1 buyerRepository1) {
        this.buyerRepository1 = buyerRepository1;
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

}
