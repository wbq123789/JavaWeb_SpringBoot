package com.example.service.impl;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetails;
import com.example.mapper.BookMapper;
import com.example.mapper.UserMapper;
import com.example.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public List<Book> GetBookList() {
        return bookMapper.GetBookList();
    }

    @Override
    public void borrowBook(int bid, int id) {
        Integer sid=userMapper.getSidById(id);
        if (sid == null) return;
        bookMapper.addBorrow(bid, sid);
    }

    @Override
    public int deleteBorrow(int id) {
        return bookMapper.deleteBorrow(id);
    }

    @Override
    public void DeleteBook(int bid) {
        bookMapper.DeleteBook(bid);
    }

    @Override
    public int getBookCount() {
        return bookMapper.getBooksCount();
    }

    @Override
    public int getBorrowCount() {
        return bookMapper.getBorrowCount();
    }

    @Override
    public void addBook(String title, String desc, double price) {
        bookMapper.addBook(title,desc,price);
    }

    @Override
    public List<Book> getBorrowedBookById(int id) {
        Integer sid=userMapper.getSidById(id);
        if (sid == null)
            return Collections.emptyList();
        else
            return bookMapper.BorrowListBySid(sid);
    }

    @Override
    public List<Book> getAllBookWithoutBorrow() {
        List<Book> books=bookMapper.GetBookList();
        List<Integer> borrows=bookMapper.getBorrowList()
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());
        return books
                .stream()
                .filter(book -> !borrows.contains(book.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowDetails> BorrowDetailsList() {
        return bookMapper.BorrowDetailsList();
    }

}
