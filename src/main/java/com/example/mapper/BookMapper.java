package com.example.mapper;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> GetBookList();
    @Select("select count(*) from book")
    int getBooksCount();
    @Select("select count(*) from borrow")
    int getBorrowCount();
    @Delete("delete from book where bid = #{bid}")
    void DeleteBook(int bid);
    @Insert("insert into borrow(sid,bid,time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("bid")int bid,@Param("sid")int sid);
    @Delete("delete from borrow where bid = #{bid}")
    int deleteBorrow(int bid);
    @Insert("insert into book(title,`desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title")String title, @Param("desc") String desc, @Param("price")double price);

    @Select("select * from book where bid in (select bid from borrow where sid = #{sid})")
    List<Book> BorrowListBySid(int sid);
    @Select("select * from borrow")
    List<Borrow> getBorrowList();
    @Results({
            @Result(column = "bid",property = "book_id"),
            @Result(column = "title",property = "book_name"),
            @Result(column = "time",property = "time"),
            @Result(column = "name",property = "student_name"),
            @Result(column = "sid",property = "student_id"),
    })
    @Select("SELECT * FROM borrow LEFT JOIN book on book.bid = borrow.bid LEFT JOIN student ON borrow.sid = student.sid")
    List<BorrowDetails> BorrowDetailsList();
}
