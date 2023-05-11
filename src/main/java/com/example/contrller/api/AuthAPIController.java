package com.example.contrller.api;

import com.example.service.AuthService;
import com.example.service.VerifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/auth")
public class AuthAPIController {
    @Resource
    AuthService service;
    @Resource
    VerifyService verifyService;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String name,
                             @RequestParam("sex") String sex,
                             @RequestParam("grade") String grade,
                             @RequestParam("email") String email,
                             @RequestParam("ver-code") String code,
                             @RequestParam("password") String password){
        if (verifyService.doVerify(email,code)) {
            if (service.register(name, sex, grade, password)){
                return "redirect:/login";
                //return new RestBean(200,"注册成功!");
            }else
                return "redirect:/register";
                //return new RestBean(401,"注册失败，学生信息有误！");
        }else
            return "redirect:/register";
            //return new RestBean(402,"注册失败，注册码填写错误！");
    }
}
