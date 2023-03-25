package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Book;

import java.util.List;

public interface BookDao {
    Book getOne(Long id);

    List<Book> getAll();

    void buyBook(Long id, Long num); // buy book directly, not through cart

    void deleteBook(Long id);

    void addBook(String title, String author, String isbn, Float price, String pub, Long stock, Long sales);

    void modTitle(Long id, String title);

    void modAuthor(Long id, String author);

    void modIsbn(Long id, String isbn);

    void modPrice(Long id, Float price);

    void modPub(Long id, String pub);
    

}
