package com.example.contrller.api;

import com.example.entity.RestBean;
import com.example.service.VerifyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/register")
public class RegisterAPIController {
    @Resource
    VerifyService service;
    @RequestMapping("/verify-code")
    public RestBean VerifyCode(@RequestParam("email")String email){
        try {
            service.sendVerifyCode(email);
            return new RestBean(200,"邮件发送成功！");
        }catch (Exception e)
        {
            return new RestBean(500,"错误的邮件地址！");
        }
    }

}
