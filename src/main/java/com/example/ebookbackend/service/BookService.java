package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.Book;

import java.util.List;


public interface BookService {
    Book findBookById(Long id);

    List<Book> getAllBooks();


    void buyBooks(Long id, Long num); // only modify stocks and sales

    void addOldBook(Long id, Long num); // replenish books

    void modBook(Book book) throws Exception;

    void addNewBook(String title, String author, String isbn, Float price, String pub, Long stock, String pic_url); // add a new book

    void deleteBook(Long id);

    void modPic(Long id, String cover);
}
