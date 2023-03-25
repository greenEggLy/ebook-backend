package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.entity.Picture;
import com.example.ebookbackend.repository.BookRepository;
import lombok.Builder;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BookDaoImpl implements BookDao {

    private BookRepository bookRepository;

    @Override
    public Book getOne(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void buyBook(Long id, Long num) {
        // add order and add order number should be implemented in other dao?
        Book book = getOne(id);
        book.setSales(book.getSales() + num);
        book.setStock(book.getStock() - num);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public void addBook(String title, String author, String isbn, Float price, String pub, Long stock, Long sales) {
        sales = 0L;
        Book book = Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .price(price)
                .pub(pub)
                .stock(stock)
                .sales(sales).build();
        bookRepository.save(book);
    }

    @Override
    public void modTitle(Long id, String title) {
        Book book = getOne(id);
        book.setTitle(title);
    }

    @Override
    public void modAuthor(Long id, String author) {
        Book book = getOne(id);
        book.setAuthor(author);
    }

    @Override
    public void modIsbn(Long id, String isbn) {
        Book book = getOne(id);
        book.setIsbn(isbn);
    }

    @Override
    public void modPrice(Long id, Float price) {
        Book book = getOne(id);
        book.setPrice(price);
    }

    @Override
    public void modPub(Long id, String pub) {
        Book book = getOne(id);
        book.setPub(pub);
    }


}

