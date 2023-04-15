package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Book;

import java.util.List;


public interface BookDao {

    Book findOne(Long id);

    Book getOne(Long id);

    List<Book> getAll();

    void deleteBook(Long id);

    void buyBook(Long id, Long num);

    void addBook(String title, String author, String isbn, Float price, String pub, Long stock, Long sales, String pic_url);

    void modBook(Book book);

    void modPic(Long id, String pic_url);

    void addStock(Long id, Long num);

    void minusStock(Long id, Long num);

    void addSales(Long id, Long num);

}
