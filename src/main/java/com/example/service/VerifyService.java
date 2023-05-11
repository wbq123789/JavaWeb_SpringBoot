package com.example.service;

import org.springframework.stereotype.Service;

public interface VerifyService {
    void sendVerifyCode(String email);
    boolean doVerify(String email,String code);
}
