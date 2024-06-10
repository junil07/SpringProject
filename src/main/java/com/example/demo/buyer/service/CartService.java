package com.example.demo.buyer.service;

import com.example.demo.buyer.entity.Cart;
import com.example.demo.buyer.repository.CartRepository;
import com.example.demo.seller.repository.BuyerRepository;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerRepository buyerRepository;

    public void insertCartItem(Cart items,String buyerId){
        Cart cart = new Cart();
        cart.setProduct(items.getProduct());
        cart.setBuyer(buyerRepository.findByBuyerId(buyerId));
        cart.setCartProductCount(items.getCartProductCount());
        cart.setCartProductSize(items.getCartProductSize());

        cartRepository.save(cart);
    }

    public List<Cart> getCartList(String buyerId){
       return cartRepository.findByBuyer(buyerRepository.findByBuyerId(buyerId));
    }

    public void updateCartProductCount(int cartId, int newCount){
        Cart cart=cartRepository.findById(cartId).orElse(null);
        if(cart != null){
            cart.setCartProductCount(newCount);
            cartRepository.save(cart);
        }else{
            throw new RuntimeException("Cart Id : cartId");
        }
    }

    public void deleteCartProduct(List<Integer> cartIds){
        for(int cartId:cartIds) {
            cartRepository.deleteById(cartId);
        }
    }

    public Integer selectMaxCartId(){
        return cartRepository.findMaxCartId();
    }
}
