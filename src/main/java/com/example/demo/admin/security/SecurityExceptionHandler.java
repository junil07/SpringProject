package com.example.demo.admin.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// 전역적인 예외 처리 로직을 정의할 수 있게 해주는 애노테이션
// 컨트롤러 전반에 발생하는 예외를 처리하는 데 사용
@ControllerAdvice
public class SecurityExceptionHandler {

    /*
        1. ExceptionHandler - ControllerAdvice가 적용된 클래스에서 사용가능하며,
        특정 예외 타입이 발생했을 때 실행할 메서드를 지정함.
        2. ResponseStatus - ExceptionHandler과 함께 사용되며, 예외가 발생했을 때
        반환할 HTTP상태 코드를 지정함. ex) @ResponseStatus(HttpStatus.NOT_FOUND)는 404 Not Found 상태 코드를 반환함.
     */

    @ExceptionHandler(CustomNotfoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(CustomNotfoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        System.out.println(exception.getMessage() + "여기 404404 한명이요heheboai");
        return "/error/404";
    }


}
