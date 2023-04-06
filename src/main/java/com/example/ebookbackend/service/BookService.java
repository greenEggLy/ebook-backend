package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.Book;

import java.util.List;


public interface BookService {
    Book findBookById(Long id);

    List<Book> getAllBooks();

    void buyBooks(Long id, Long num); // only modify stocks and sales

    void addOldBook(Long id, Long num); // replenish books

    void addNewBook(String title, String author, String isbn, Float price, String pub, Long stock); // add a new book

    void deleteBook(Long id);

    void modTitle(Long id, String title);

    void modAuthor(Long id, String author);

    void modIsbn(Long id, String isbn);

    void modPrice(Long id, Float price);

    void modPub(Long id, String pub);
}
