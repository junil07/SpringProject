package com.example.demo.buyer.controller;

import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.buyer.entity.Cart;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CartService;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.service.ProductImageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SecurityServiceImple securityService;

    @RequestMapping("/buyer/cart")
    public String main(@AuthenticationPrincipal User user, Principal principal, Model model){
        String buyerId="";
        if (principal != null) { // 로그인이 되었을 때 이야기임 로그인이 안되었을 때 예외는 if else 밖에
            if (securityService.hasRole(user, "ROLE_BUYER")) { // 만약 '구매자'의 권한을 갖고 있다면
                buyerId = user.getUsername();
            } else { // 다른 권한을 갖고 있거나
            }
        }else{
            buyerId=null;
        }
        List<Category> categories = categoryService.getRows();
        List<Cart> cartList = cartService.getCartList(buyerId);
        List<ProductImage> productImages = new ArrayList<>();
        for(Cart cart : cartList){
            Product product=cart.getProduct();

            ProductImage images = productImageService.getProductImage(product);

            productImages.add(images);
        }
        for(ProductImage image : productImages) {
            System.out.println(image.getProductImageExtension()); // 또는 image.toString()
            System.out.println(image.getProductImageSname()); // 또는 image.toString()
        }
        model.addAttribute("categories", categories);
        model.addAttribute("productImageList", productImages);
        model.addAttribute("cartList", cartList);
        return "/buyer/productCart";
    }

    @PostMapping("/updateCartProductCount")
    public ResponseEntity<?> updateCartProductCount(@RequestParam("cartId") int cartId, @RequestParam("newCount") int newCount){
        cartService.updateCartProductCount(cartId,newCount);
        return ResponseEntity.ok().body("수량 변경 성공 !!");
    }

    @PostMapping("/deleteSelectedCartItems")
    public ResponseEntity<?> deleteCartProduct(@RequestParam("cartIds") List<Integer> cartIds){
        cartService.deleteCartProduct(cartIds);
        return ResponseEntity.ok().body("장바구니 상품 삭제 성공 !!");
    }
}
