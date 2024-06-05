package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.Cart;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.service.CartService;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.service.ProductImageService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/buyer/cart")
    public String main(Model model){
        List<Category> categories = categoryService.getRows();
        List<Cart> cartList = cartService.getCartList("jiwon15");
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

    @RequestMapping("/cart/insert")
    public ResponseEntity<?> insertCart(@RequestBody List<Cart> cartItems){

        for(Cart items : cartItems){
            System.out.print(items.getCartProductSize());
            System.out.print(items.getCartProductCount());
            cartService.insertCartItem(items);
        }

        return ResponseEntity.ok().body("장바구니 성공 !!");
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
