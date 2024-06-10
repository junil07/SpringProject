package com.example.demo.buyer.controller;

import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.service.CartService;
import com.example.demo.buyer.service.StockService;
import com.example.demo.seller.service.Order_listService;
import com.example.demo.seller.service.OrderitemService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private Order_listService orderListService;
    @Autowired
    private OrderitemService orderitemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private StockService stockService;

    private IamportClient iamportClient;

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @PostMapping("/payment/validation/{imp_uid}")
    @ResponseBody
    public ResponseEntity<?> validateIamport(@PathVariable String imp_uid) {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 검증 중 오류 발생");
        }
    }

    @PostMapping("/save_payment")
    public ResponseEntity<?> savePayment(@RequestBody Map<String, Object> paymentData) {
        try {
            String buyerId = (String) paymentData.get("buyerId");
            String buyerAddress = (String) paymentData.get("buyerAddress");
            int payPrice = Integer.parseInt(paymentData.get("payPrice").toString());
            List<Long> productIds = ((List<?>) paymentData.get("productIds")).stream()
                    .map(id -> Long.parseLong(id.toString()))
                    .collect(Collectors.toList());
            List<Integer> productCounts = ((List<?>) paymentData.get("productCounts")).stream()
                    .map(count -> Integer.parseInt(count.toString()))
                    .collect(Collectors.toList());
            List<Integer> productPrices = ((List<?>) paymentData.get("productPrices")).stream()
                    .map(price -> Integer.parseInt(price.toString()))
                    .collect(Collectors.toList());
            List<Integer> cartIds = ((List<?>) paymentData.get("cartIds")).stream()
                    .map(cart -> Integer.parseInt(cart.toString()))
                    .collect(Collectors.toList());
            List<Integer> productSize = ((List<?>) paymentData.get("productSize")).stream()
                    .map(size -> Integer.parseInt(size.toString()))
                    .collect(Collectors.toList());
            System.out.println("buyerId: " + buyerId);
            System.out.println("buyerAddress: " + buyerAddress);
            System.out.println("payPrice: " + payPrice);
            System.out.println("productIds: " + productIds);
            System.out.println("productCounts: " + productCounts);
            System.out.println("productPrices: " + productPrices);


            orderListService.insertOrderList(buyerId, payPrice, buyerAddress);
            System.out.println("insert성공!!");
            orderitemService.insertOrderItem(productIds, productCounts, productPrices);
            System.out.println("insert성공!!");
            cartService.deleteCartProduct(cartIds);
            System.out.println("delete성공!!");
            stockService.updateStock(productIds,productCounts,productSize);

            return ResponseEntity.ok("결제 정보가 성공적으로 저장되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 정보 저장 중 오류 발생");
        }
    }
}
