package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    UserMapper mapper;

    @Transactional
    @Override
    public void register(String username, String sex, String grade, String password) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        User user=new User(0,username,"user",encoder.encode(password));
        if(mapper.registerUser(user)<=0)
            throw new RuntimeException("用户基本信息添加失败！");
        if (mapper.addStudentInfo(user.getId(),username,sex,grade)<=0)
            throw new RuntimeException("学生详细信息插入失败！");
    }

    @Override
    public int getStudentCount() {
        return mapper.getStudentsCount();
    }
}
