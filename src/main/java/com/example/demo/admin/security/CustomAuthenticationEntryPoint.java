package com.example.demo.admin.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("AuthenticationException 발생: " + authException.getMessage());
        request.setAttribute("errorMessage", authException.getMessage());
        System.out.println("AuthenticationException 발생2: " + authException.getMessage());
        response.sendRedirect("/error/401");
    }

}
