package com.example.service;

public interface AuthService {
    boolean register(String username, String sex, String grade, String password);
    int getStudentCount();
}
