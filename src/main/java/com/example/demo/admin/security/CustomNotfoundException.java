package com.example.demo.admin.security;

public class CustomNotfoundException extends RuntimeException {
    public CustomNotfoundException(String message) {
        super(message);
    }
}
