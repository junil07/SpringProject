package com.example.demo.admin.security;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.repository.BuyerRepository1;
import com.example.demo.admin.repository.SellerRepository1;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuyerDetailsServiceImple implements BuyerDetailsService {

    private BuyerRepository1 buyerRepository;
    private SellerRepository1 sellerRepository1;

    public BuyerDetailsServiceImple(BuyerRepository1 buyerRepository
            , SellerRepository1 sellerRepository1) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository1 = sellerRepository1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n사용자 loadUserByUsername 호출됨!!\n");
        Buyer buyer = buyerRepository.findBybuyerId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        return User.builder().username(buyer.getBuyerId()).password(buyer.getBuyerPassword()).roles(Role.BUYER.name()).build();
    }
}
