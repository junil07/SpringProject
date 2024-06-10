package com.example.demo.admin.service;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.repository.SellerRepository1;
import com.example.demo.seller.DTO.OrderitemDTO;
import com.example.demo.seller.DTO.SellerDTO;
import com.example.demo.seller.domain.Orderitem;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImple implements SellerService {

    private SellerRepository1 sellerRepository1;
    private PasswordEncoder passwordEncoder;

    public SellerServiceImple(SellerRepository1 sellerRepository1, PasswordEncoder passwordEncoder) {
        this.sellerRepository1 = sellerRepository1;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    @Override
    public boolean sellerPwdUpdate(String sellerId, String sellerPwd) {
        boolean flag = false;
        Seller seller = sellerRepository1.findBysellerId(sellerId).orElse(null);

        if (seller != null) {
            seller.setSellerPassword(passwordEncoder.encode(sellerPwd));
            flag = true;
        }

        return flag;
    }

    @Override
    public int idCheck(String sellerId) {
        Seller seller = sellerRepository1.findBysellerId(sellerId).orElse(null);
        int check = 0;

        if (seller != null) {
            check = 1;
        }

        return check;
    }

    @Transactional
    @Override
    public Seller register(Seller seller) {
        return sellerRepository1.save(seller);
    }

    // 아이디 찾기
    @Override
    public Seller idFind(String sellerName, String sellerEmail) {
        Seller seller = sellerRepository1.findBySellerNameAndSellerEmail(sellerName, sellerEmail).orElse(null);
        return seller;
    }

    @Override
    public Seller sellerFind(String sellerId, String sellerEmail) {
        Seller seller = sellerRepository1.findBySellerIdAndSellerEmail(sellerId, sellerEmail).orElse(null);
        return seller;
    }

    // Seller 비밀번호 전부 암호화 하는데 사용했음
    public boolean authenticate() {
        List<Seller> sellerList = sellerRepository1.findAll();

        for (Seller test : sellerList) {
            System.out.println(test.getSellerId() + "\t\t" + test.getSellerPassword());
//            test.setSellerPassword(passwordEncoder.encode(test.getSellerPassword()));
//            sellerRepository1.save(test);
        }
        return true;
    }

    //박승섭 추가내용
    @Override
    public List<Seller> getSellersList(String sellerId) {
        return sellerRepository1.findBySellerId(sellerId);
    }

    public void updateMypage(String userId, List<SellerDTO> sellers) {
        try {
            System.out.println("Updating mypage for userId: " + userId); // 로그 추가
            System.out.println("SellerDTOs: " + sellers); // 로그 추가

            List<Seller> sellerList = sellerRepository1.findBySellerId(userId);

            if (!sellerList.isEmpty()) {
                for (SellerDTO sellerDTO : sellers) {
                    Seller seller = sellerList.stream()
                            .filter(s -> s.getSellerId().equals(userId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("해당 셀러를 찾을 수 없습니다."));

                    // SellerDTO의 정보를 사용하여 기존 셀러 정보를 업데이트합니다.
                    seller.setSellerAddress(sellerDTO.getSellerAddress());
                    seller.setSellerPhoneNum(sellerDTO.getSellerPhonenum());
                    seller.setSellerEmail(sellerDTO.getSellerEmail());
                    // 필요에 따라 다른 필드들도 업데이트합니다.

                    // 업데이트된 셀러 정보를 저장합니다.
                    sellerRepository1.save(seller);
                }
            } else {
                throw new RuntimeException("해당 셀러를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
