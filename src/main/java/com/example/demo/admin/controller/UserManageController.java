package com.example.demo.admin.controller;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.admin.Entity.Seller;
import com.example.demo.admin.model.Activation;
import com.example.demo.admin.model.BuyerInfo;
import com.example.demo.admin.model.Grade;
import com.example.demo.admin.model.SellerInfo;
import com.example.demo.admin.service.BuyerServiceImple;
import com.example.demo.admin.service.SellerServiceImple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class UserManageController {

    private BuyerServiceImple buyerService;
    private SellerServiceImple sellerService;
    private Activation activation;
    private Grade grade;
    private BuyerInfo buyerInfo;
    private SellerInfo sellerInfo;

    public UserManageController(BuyerServiceImple buyerService, SellerServiceImple sellerService,
                                Activation activation, Grade grade,
                                BuyerInfo buyerInfo, SellerInfo sellerInfo) {
        this.buyerService = buyerService;
        this.sellerService = sellerService;
        this.activation = activation;
        this.grade = grade;
        this.buyerInfo = buyerInfo;
        this.sellerInfo = sellerInfo;
    }

    // 구매자 관리
    @RequestMapping("buyermanagement")
    public String buyermanagement(Model model) {
        String[] column = {"아이디", "이름", "생년월일", "이메일", "주소", "전화번호", "계정\n활성화", "마지막 로그인", "등급"};
        List columnName = new ArrayList<>();
        for (int i = 0; i < column.length; i++) {
            columnName.add(column[i]);
        }
        List<Buyer> buyerList = buyerService.getBuyerList();

        model.addAttribute("columnName", columnName);
        model.addAttribute("buyerList", buyerList);
        model.addAttribute("grade", grade);
        model.addAttribute("activation", activation);
        model.addAttribute("buyerInfo", buyerInfo);

        return "admin/menu/buyermanagement";
    }

    // 구매자 관리 - 계정 활성화 변경
    @RequestMapping("buyer/ActivationUpdate")
    public String buyeractivationUpdate(Activation activation, Model model) {

        List<String> checkList = buyerService.updateActivation(activation.getActivationId(), (short) activation.getActivationUpdate());

        if (checkList.contains("1") && !checkList.contains("0")) {
            model.addAttribute("error", "변경하였습니다");
        } else if (checkList.contains("1") && checkList.contains("0")) {
            model.addAttribute("error", "중간에 오류가 발생하였습니다");
        } else {
            model.addAttribute("error", "변경에 실패하였습니다");
        }

        return "admin/proc/buyerupdatecheck";
    }

    // 구매자 관리 - 등급 변경
    @RequestMapping("buyer/GradeUpdate")
    public String buyergradeUpdate(Grade grade, Model model) {

        List<String> checkList = buyerService.updateGrade(grade.getGradeId(), (short) grade.getGradeUpdate());

        if (checkList.contains("1") && !checkList.contains("0")) {
            model.addAttribute("error", "변경하였습니다");
        } else if (checkList.contains("1") && checkList.contains("0")) {
            model.addAttribute("error", "중간에 오류가 발생하였습니다");
        } else {
            model.addAttribute("error", "변경에 실패하였습니다");
        }

        return "admin/proc/buyerupdatecheck";
    }

    // 구매자 관리 - 정보 변경
    @RequestMapping("buyer/buyerInfoUpdate")
    public String buyerInfoUpdate(BuyerInfo buyerInfo, Model model) {

        boolean flag = false;
        String msg = "";

        System.out.println(buyerInfo.getAddress());
        System.out.println(buyerInfo.getId());
        System.out.println(buyerInfo.getCheck());
        System.out.println(buyerInfo.getBirth());
        System.out.println(buyerInfo.getName());
        System.out.println(buyerInfo.getPhoneNum());
        System.out.println(buyerInfo.getEmail());

        if (buyerInfo.getCheck().equals("1")) {
            flag = buyerService.buyerNameUpdate(buyerInfo.getId(), buyerInfo.getName());
        } else if (buyerInfo.getCheck().equals("2")) {
            flag = buyerService.buyerBirthUpdate(buyerInfo.getId(), buyerInfo.getBirth());
        } else if (buyerInfo.getCheck().equals("3")) {
            flag = buyerService.buyerEmailUpdate(buyerInfo.getId(), buyerInfo.getEmail());
        } else if (buyerInfo.getCheck().equals("4")) {
            flag = buyerService.buyerAddrUpdate(buyerInfo.getId(), buyerInfo.getAddress());
        } else if (buyerInfo.getCheck().equals("5")) {
            flag = buyerService.buyerPhoneUpdate(buyerInfo.getId(), buyerInfo.getPhoneNum());
        }

        if (flag) {
            msg = "변경하였습니다";
        } else {
            msg = "변경에 실패하였습니다";
        }

        model.addAttribute("error", msg);

        return "admin/proc/buyerupdatecheck";
    }

    // 판매자 관리
    @RequestMapping("sellermanagement")
    public String sellermanagement(Model model) {
        String[] column = {"아이디", "이름", "생년월일", "이메일", "주소", "전화번호", "사업자 번호", "계정 활성화", "마지막 로그인"};
        List columnName = new ArrayList<>();
        for (int i = 0; i < column.length; i++) {
            columnName.add(column[i]);
        }
        List<Seller> sellerList = sellerService.getSellerList();

        boolean flag = buyerService.authenticate("11", "11");

        model.addAttribute("columnName", columnName);
        model.addAttribute("sellerList", sellerList);
        model.addAttribute("activation", activation);
        model.addAttribute("sellerInfo", sellerInfo);

        return "admin/menu/sellermanagement";
    }

    // 판매자 관리 - 계정 활성화 변경
    @RequestMapping("seller/ActivationUpdate")
    public String sellerActivationUpdate(Activation activation, Model model) {

        List<String> checkList = sellerService.updateActivation(activation.getActivationId(), (short) activation.getActivationUpdate());

        if (checkList.contains("1") && !checkList.contains("0")) {
            model.addAttribute("error", "변경하였습니다");
        } else if (checkList.contains("1") && checkList.contains("0")) {
            model.addAttribute("error", "중간에 오류가 발생하였습니다");
        } else {
            model.addAttribute("error", "변경에 실패하였습니다");
        }

        model.addAttribute("redirect", "/admin/sellermanagement");

        return "admin/proc/sellerupdatecheck";
    }

    // 판매자 관리 - 정보 변경
    @RequestMapping("seller/sellerInfoUpdate")
    public String sellerInfoUpdate(SellerInfo sellerInfo, Model model) {

        boolean flag = false;
        String msg = "";

        if (sellerInfo.getCheck().equals("1")) {
            flag = sellerService.sellerNameUpdate(sellerInfo.getId(), sellerInfo.getName());
        } else if(sellerInfo.getCheck().equals("2")) {
            flag = sellerService.sellerBirthUpdate(sellerInfo.getId(), sellerInfo.getBirth());
        } else if(sellerInfo.getCheck().equals("3")) {
            flag = sellerService.sellerEmailUpdate(sellerInfo.getId(), sellerInfo.getEmail());
        } else if(sellerInfo.getCheck().equals("4")) {
            flag = sellerService.sellerAddrUpdate(sellerInfo.getId(), sellerInfo.getAddress());
        } else if(sellerInfo.getCheck().equals("5")) {
            flag = sellerService.sellerPhoneUpdate(sellerInfo.getId(), sellerInfo.getPhoneNum());
        } else if(sellerInfo.getCheck().equals("6")) {
            flag = sellerService.sellerBNumUpdate(sellerInfo.getId(), sellerInfo.getbNum());
        }

        if (flag) {
            msg = "변경하였습니다";
        } else {
            msg = "변경에 실패하였습니다";
        }

        model.addAttribute("error", msg);
        model.addAttribute("redirect", "/admin/sellermanagement");

        return "admin/proc/sellerupdatecheck";
    }

}
