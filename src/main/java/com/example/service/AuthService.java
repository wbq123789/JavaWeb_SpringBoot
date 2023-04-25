package com.example.service;

public interface AuthService {
    void register(String username, String sex, String grade, String password);
    int getStudentCount();
}
