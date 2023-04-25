package com.example.contrller.page;

import com.example.entity.User;
import com.example.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller
@RequestMapping("/page/user")
@PreAuthorize("hasRole('user')")
public class UserPageController {

    @Resource
    BookService service;

    @RequestMapping("/index")
    public String index(Model model, @SessionAttribute("user") User user){
        model.addAttribute("NoBorrowBookList",service.getAllBookWithoutBorrow());
        model.addAttribute("user",user);
        return "user/index";
    }
    @RequestMapping("/book")
    public String book(Model model, @SessionAttribute("user") User user){
        model.addAttribute("user",user);
        model.addAttribute("BorrowedBookList",service.getBorrowedBookById(user.getId()));
        return "user/book";
    }
}
