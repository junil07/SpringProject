package com.example.demo.buyer.controller;

import com.example.demo.buyer.DTO.CartRequest;
import com.example.demo.buyer.entity.Cart;
import com.example.demo.buyer.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CartAjaxController {

    // 생성자
    public CartAjaxController(){

    }

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/insert")
    public int insertCart(@RequestBody CartRequest cartRequest) {
        String buyerId = cartRequest.getBuyerId();
        List<Cart> cartItems = cartRequest.getCartItems();

        // Print the buyerId
        System.out.println("Buyer ID: " + buyerId);

        // Print the cart items
        for (Cart items : cartItems) {
            System.out.println("Product Size: " + items.getCartProductSize());
            System.out.println("Product Count: " + items.getCartProductCount());
            cartService.insertCartItem(items,buyerId);
        }

        System.out.println("Insert 성공");
        //return ResponseEntity.ok().body("장바구니 성공 !!");
        return 1;
    }

    @RequestMapping("/cart/direct")
    public ResponseEntity<?> directBuy(@RequestBody CartRequest cartRequest) {
        String buyerId = cartRequest.getBuyerId();
        List<Cart> cartItems = cartRequest.getCartItems();
        List<Integer> cartIds = new ArrayList<>();

        for(Cart items : cartItems){
            System.out.print(items.getCartProductSize());
            System.out.print(items.getCartProductCount());
            cartService.insertCartItem(items,buyerId);
            cartIds.add(cartService.selectMaxCartId());
        }

        return ResponseEntity.ok().body(Map.of("cartIds",cartIds));
    }
}
