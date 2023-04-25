package com.example.contrller.api;

import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/admin")
public class AdminAPIController {
    @Resource
    BookService bookService;
    @RequestMapping(value = "/del-book",method = RequestMethod.GET)
    public String deleteBook(int bid){
        bookService.DeleteBook(bid);
        return "redirect:/page/admin/book";
    }
    @RequestMapping(value = "/add-book",method = RequestMethod.POST)
    public String addBook(String title,String desc,double price){
        bookService.addBook(title, desc, price);
        return "redirect:/page/admin/book";
    }
}
