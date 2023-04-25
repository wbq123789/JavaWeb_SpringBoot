package com.example.contrller.page;

import com.example.entity.User;
import com.example.service.AuthService;
import com.example.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    BookService bookService;
    @Resource
    AuthService authService;

    @RequestMapping("/index")
    public String index(Model model, @SessionAttribute("user") User user){
        model.addAttribute("book_count",bookService.getBookCount());
        model.addAttribute("borrow_count",bookService.getBorrowCount());
        model.addAttribute("student_count",authService.getStudentCount());
        model.addAttribute("all_borrow_list",bookService.BorrowDetailsList());
        model.addAttribute("user",user);
        return "admin/index";
    }
    @RequestMapping("/book")
    public String book(Model model, @SessionAttribute("user") User user){
        model.addAttribute("user",user);
        model.addAttribute("BookList",bookService.GetBookList());
        return "admin/book";
    }
    @RequestMapping("/add_book")
    public String add_book(Model model, @SessionAttribute("user") User user){
        model.addAttribute("user",user);
        return "admin/add_book";
    }
}
