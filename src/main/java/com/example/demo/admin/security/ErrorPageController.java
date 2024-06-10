package com.example.demo.admin.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/")
public class ErrorPageController {

    // 401
    @RequestMapping("401")
    public String error401() {
        return "error/401";
    }

    // 403
    @RequestMapping("403")
    public String error403() {
        return "error/403";
    }

    // 404
    @RequestMapping("404")
    public String error404() {
        return "error/404";
    }

    // 500
    @RequestMapping("500")
    public String error500() {
        return "error/500";
    }

    // Seller 권한 부족
    @RequestMapping("sellerAuthentication")
    public String sellerAuthentication() {
        return "error/sellerAuthentication";
    }

    // Seller 접근 거부
    @RequestMapping("seller")
    public String errorSeller() {
        return "error/seller";
    }


}
