package com.example.ebookbackend.controller;


import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/getBooks")
    @CrossOrigin
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
