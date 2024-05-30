package com.example.demo.admin.security;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.repository.BuyerRepository1;
import com.example.demo.admin.repository.SellerRepository1;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserUserDetailsServiceImple implements UserUserDetailsService {

    private BuyerRepository1 buyerRepository;
    private SellerRepository1 sellerRepository1;

    public UserUserDetailsServiceImple(BuyerRepository1 buyerRepository
            , SellerRepository1 sellerRepository1) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository1 = sellerRepository1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Buyer buyer = buyerRepository.findBybuyerId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        return User.builder().username(buyer.getBuyerId()).password(buyer.getBuyerPassword()).roles(Role.BUYER.name()).build();
    }
}
