package com.example.ebookbackend.controller;


import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET)
    @CrossOrigin(value = "http://localhost:3000")
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/getBooks")
    @CrossOrigin(value = "http://localhost:3000")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/book/add")
    @CrossOrigin(value = "http://localhost:3000")
    public void addBook(@RequestBody Book book) {
        bookService.addNewBook(book.getTitle(), book.getAuthor(), book.getIsbn(),
                book.getPrice(), book.getPub(), book.getStock(), book.getPicture());
    }

    @RequestMapping(value = "/book/delete")
    @CrossOrigin(value = "http://localhost:3000")
    public void delBook(Long book_id) {
        bookService.deleteBook(book_id);
    }

    @RequestMapping(value = "/book/mod/picture")
    @CrossOrigin(value = "http://localhost:3000")
    public void modBookPic(@RequestParam Map<String, String> map) {
        Long book_id = Long.valueOf(map.get("book_id"));
        String pic_url = map.get("pic_url");
        bookService.modPic(book_id, pic_url);
    }

    @RequestMapping(value = "/book/mod")
    @CrossOrigin(value = "http://localhost:3000")
    public void modBook(@RequestBody Book book) {
        bookService.modBook(book);
    }

}
