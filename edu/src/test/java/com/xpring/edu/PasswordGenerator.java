package com.xpring.edu;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Bothra";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
        System.out.println(Integer.parseInt("01"));

    }
}
