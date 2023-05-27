package com.example.ebookbackend.controller;


import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.service.BookService;
import com.example.ebookbackend.utils.Msg;
import com.example.ebookbackend.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping(value="/book")
@CrossOrigin(value = "http://localhost:3000")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/book/all")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/book/add")
    public Msg addBook(@RequestBody Book book) {
        try {
            Msg msg = checkValidity(book);
            if (msg.getStatus() != MsgUtil.SUCCESS) {
                return msg;
            }
            bookService.addNewBook(book.getTitle(), book.getAuthor(), book.getIsbn(),
                    book.getPrice(), book.getPub(), book.getStock(), book.getCover());
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, e.getMessage());
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
    }

    @RequestMapping(value = "/book/delete")
    public void delBook(Long book_id) {
        bookService.deleteBook(book_id);
    }

    @PostMapping(value = "/book/cover")
    public void modBookPic(@RequestParam Map<String, String> map) {
        Long book_id = Long.valueOf(map.get("book_id"));
        String cover = map.get("cover");
        bookService.modPic(book_id, cover);
    }

    @PostMapping(value = "/book")
    public Msg modBook(@RequestBody Book book) {
        try {
            Msg msg = checkValidity(book);
            if (msg.getStatus() != MsgUtil.SUCCESS) {
                return msg;
            }
            bookService.modBook(book);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, e.getMessage());
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
    }

    private Msg checkValidity(Book book) throws Exception {
        // check validity
        if (book.getTitle() == null || book.getTitle().equals("")) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "书名不能为空");
        }
        if (book.getAuthor() == null || book.getAuthor().equals("")) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "作者不能为空");
        }
        if (book.getIsbn() == null || book.getIsbn().equals("")) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "ISBN不能为空");
        }
        if (book.getPrice() == null || book.getPrice() < 0) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "价格不能为空或小于0");
        }
        if (book.getPub() == null || book.getPub().equals("")) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "出版社不能为空");
        }
        if (book.getStock() == null || book.getStock() < 0) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "库存不能为空或小于0");
        }
        if (book.getCover() == null || book.getCover().equals("")) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "图片不能为空");
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
    }
}
