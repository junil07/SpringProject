package com.example.demo.seller.service;

import java.time.LocalDate;
import java.util.*;

import com.example.demo.seller.DTO.OrderitemDTO;
import org.hibernate.query.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.repository.OrderitemRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderitemService {
    private static final Logger logger = LoggerFactory.getLogger(OrderitemService.class);
    private final OrderitemRepository orderitemRepository;

    @Autowired
    public OrderitemService(OrderitemRepository orderitemRepository) {
        this.orderitemRepository = orderitemRepository;
    }


    public Map<String, Integer> findDisBuyer(){
        List<Object[]> resultsBuyer = orderitemRepository.findBuyerDate();
        Map<String, Integer> buyer = new HashMap<>();
        for(Object[] result: resultsBuyer){
            String date = result[0].toString();
            Integer totalBuyer = ((Number) result[1]).intValue();
            buyer.put(date, totalBuyer);
        }
        return buyer;
    }
    public Map<String, Integer> findTotalCount(){
        List<Object[]> resultsCount = orderitemRepository.findCountDate();
        Map<String, Integer> count = new HashMap<>();
        for(Object[] result: resultsCount){
            String date = result[0].toString();
            Integer totalCount = ((Number) result[1]).intValue();
            count.put(date, totalCount);
        }
        return count;
    }

    public Map<String, Integer> getTotalPricePerDate() {
        List<Object[]> resultsPrice = orderitemRepository.findTotalPricePerDate();
        Map<String, Integer> price = new HashMap<>();
        for (Object[] result : resultsPrice) {
            String date = result[0].toString();
            Integer totalPrice = ((Number) result[1]).intValue();
            price.put(date, totalPrice);
        }
        return price;
    }

    public Map<String, Integer> countsellproduct(){
        List<Object[]> resultssellproduct = orderitemRepository.findsellProduct();
        Map<String, Integer> product = new HashMap<>();
        for(Object[] result: resultssellproduct){
            String date = result[0].toString();
            Integer sellproduct = ((Number) result[1]).intValue();
            product.put(date, sellproduct);
        }
        return product;
    }

    public List<Orderitem> getOrderitemList() {
        return orderitemRepository.findAll();
    }

    public List<Object[]> findOutstandingOrders() {
        return orderitemRepository.findoutstanding();
    }

    //배송페이지-배송 승인처리
//    public void ordercheck(List<OrderitemDTO> orderItems){
//        for (OrderitemDTO orderItemDTO : orderItems) {
//            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
//            if (optionalOrderitem.isPresent()) {
//                Orderitem orderitem = optionalOrderitem.get();
//                orderitem.setOrderitemDstatus("배송중"); // orderitemCase를 "정상처리"으로 변경
//                orderitemRepository.save(orderitem);
//            } else {
//                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
//            }
//        }
//    }

    //배송페이지-배송 완료처리
//    public void ordersucess(List<OrderitemDTO> orderItems){
//        for (OrderitemDTO orderItemDTO : orderItems) {
//            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
//            if (optionalOrderitem.isPresent()) {
//                Orderitem orderitem = optionalOrderitem.get();
//                orderitem.setOrderitemDstatus("배송완료"); // orderitemCase를 "정상처리"으로 변경
//                orderitemRepository.save(orderitem);
//            } else {
//                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
//            }
//        }
//    }

    //배송페이지-배송 거부(철회)처리
//    public void ordercancel(List<OrderitemDTO> orderItems){
//        for (OrderitemDTO orderItemDTO : orderItems) {
//            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
//            if (optionalOrderitem.isPresent()) {
//                Orderitem orderitem = optionalOrderitem.get();
//                orderitem.setOrderitemDstatus("정상처리"); // orderitemCase를 "정상처리"으로 변경
//                orderitemRepository.save(orderitem);
//            } else {
//                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
//            }
//        }
//    }

    //배송현황 관리 페이지 - 배송처리
    public void deliverycheck(List<OrderitemDTO> orderItems){
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemDstatus("배송중"); // orderitemCase를 "정상처리"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //배송현황 관리 페이지 - 배송완료
    public void deliverysucess(List<OrderitemDTO> orderItems){
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemDstatus("배송완료"); //
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //배송현황 관리 페이지 - 구매확정요청
    public void sucesscheck(List<OrderitemDTO> orderItems){
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("구매확정요청"); // orderitemCase를 "정상처리"으로 변경
                orderitem.setOrderitemDate(LocalDate.now()); // orderitemDate를 오늘 날짜로 업데이트
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //취소페이지-취소 승인처리
    public void updateOrderitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("취소승인"); // orderitemCase를 "취소 승인"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //취소페이지-취소 철회처리
    public void withdrawOrderitemCase(List<OrderitemDTO> orderItems){
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("정상처리"); // orderitemCase를 "정상처리"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }
    
    //빈픔페이지-반픔 승인처리
    public void updatereutrnitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("반품수거중"); // orderitemCase를 "반품수거중"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //빈픔페이지-반픔 승인처리
    public void updatesucessitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("반품완료"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //빈픔페이지-반픔 거부(철회)처리
    public void updatecancelitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("정상처리"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }


    //교환페이지 - 교환재배송처리
    public void updatexchangeitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("교환수거중"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //교환페이지 - 교환완료처리
    public void updateexsucessitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("교환완료"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //교환페이지 - 교환 거부(철회)처리
    public void updatefailitemCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("정상처리"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

    //교환페이지 - 반품으로 변경
    public void updatereturnCase(List<OrderitemDTO> orderItems) {
        for (OrderitemDTO orderItemDTO : orderItems) {
            Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderItemDTO.getOrderitemId());
            if (optionalOrderitem.isPresent()) {
                Orderitem orderitem = optionalOrderitem.get();
                orderitem.setOrderitemCase("반품요청"); // orderitemCase를 "반품완료"으로 변경
                orderitemRepository.save(orderitem);
            } else {
                throw new RuntimeException("Orderitem not found: " + orderItemDTO.getOrderitemId());
            }
        }
    }

}