package com.example.service;

import com.example.entity.Book;
import com.example.entity.BorrowDetails;

import java.util.List;

public interface BookService {
    List<Book> GetBookList();
    void borrowBook(int bid, int sid);
    int deleteBorrow(int id);
    void DeleteBook(int bid);
    int getBookCount();
    int getBorrowCount();
    void addBook(String title,String desc,double price);
    List<Book> getBorrowedBookById(int id);
    List<Book> getAllBookWithoutBorrow();
    List<BorrowDetails> BorrowDetailsList();
}
