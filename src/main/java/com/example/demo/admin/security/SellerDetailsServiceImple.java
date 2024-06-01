package com.example.demo.admin.security;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.repository.SellerRepository1;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailsServiceImple implements SellerDetailsService {

    private SellerRepository1 sellerRepository;

    public SellerDetailsServiceImple(SellerRepository1 sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("seller loadUserByUsername 호출됨");
        Seller seller = sellerRepository.findBysellerId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));
        return User.builder().username(seller.getSellerId()).password(seller.getSellerPassword()).roles(Role.SELLER.name()).build();
    }
}