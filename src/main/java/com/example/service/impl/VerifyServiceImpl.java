package com.example.service.impl;

import com.example.service.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Resource
    JavaMailSender sender;
    @Resource
    StringRedisTemplate template;
    @Value("${spring.mail.username}")
    String from;
    @Override
    public void sendVerifyCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message. setSubject("图书管理系统-注册");
        Random random = new Random();
        String code = String.valueOf(random.nextInt(899999)+100000);
        template.opsForValue().set("verify:code:"+email,code,10, TimeUnit.MINUTES);
        message.setText("欢迎使用本系统!   您的注册验证码为:"+code+"   十分钟内有效，请及时完成注册！ 如果不是本人操作，请忽略。");
        message.setTo(email);
        message.setFrom(from);
        sender.send(message);
    }

    @Override
    public boolean doVerify(String email, String code) {
        String Code =template.opsForValue().get("verify:code:"+email);
        if( Code != null && Code.equals(code)) {
            template.delete("verify:code:"+email);
            return true;
        }
        else
            return false;
    }
}
