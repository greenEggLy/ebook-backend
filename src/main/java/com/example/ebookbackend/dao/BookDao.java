package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Book;

import java.util.List;


public interface BookDao {

    Book findOne(Long id);

    Book getOne(Long id);

    List<Book> getAll();

    void deleteBook(Long id);

    void buyBook(Long id, Long num) throws Exception;

    Book addBook(Book book);

    void modBook(Book book) throws Exception;

    Book modPic(Long id, String cover);

    void addStock(Long id, Long num);

    void minusStock(Long id, Long num);

    void addSales(Long id, Long num);

}
