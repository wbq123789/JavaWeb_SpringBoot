package com.example;

import com.example.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootWebTestApplicationTests {
    @Resource
    TestMapper mapper;
    @Test
    void contextLoads() {
        mapper.getSid();
        mapper.getSid();
        System.out.println(mapper.getSid());
    }
}
