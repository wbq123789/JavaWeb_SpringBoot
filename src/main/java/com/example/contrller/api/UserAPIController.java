package com.example.contrller.api;

import com.example.entity.User;
import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/user")
public class UserAPIController {
    @Resource
    BookService bookService;
    @RequestMapping(value = "/borrow-book",method = RequestMethod.GET)
    public String borrowBook(@RequestParam("bid") int bid, @SessionAttribute("user")User user){
        bookService.borrowBook(bid,user.getId());
        return "redirect:/page/user/index";
    }

    @RequestMapping(value = "/delete-Borrow",method = RequestMethod.GET)
    public String deleteBorrow(@RequestParam("bid") int bid){
        bookService.deleteBorrow(bid);
        return "redirect:/page/user/book";
    }
}
