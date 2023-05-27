package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Long id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }


    @Override
    public void buyBooks(Long id, Long num) {
        bookDao.addSales(id, num);
        bookDao.minusStock(id, num);
    }

    @Override
    public void addOldBook(Long id, Long num) {
        bookDao.addStock(id, num);
    }

    @Override
    public void modBook(Book book) throws Exception {
        bookDao.modBook(book);
    }

    @Override
    public void addNewBook(String title, String author, String isbn, Float price, String pub, Long stock, String pic_url) {
        bookDao.addBook(title, author, isbn, price, pub, stock, 0L, pic_url);
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void modPic(Long id, String cover) {
        bookDao.modPic(id, cover);
    }

}
