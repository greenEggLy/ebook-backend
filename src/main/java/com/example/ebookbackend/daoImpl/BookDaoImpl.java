package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.entity.Picture;
import com.example.ebookbackend.repository.BookRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book getOne(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

//    @Override
//    public void buyBook(Long id, Long num) {
//        // add order and add order number should be implemented in other dao?
//        Book book = getOne(id);
//        book.setSales(book.getSales() + num);
//        book.setStock(book.getStock() - num);
//    }

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
        bookRepository.save(book);
    }

    @Override
    public void modAuthor(Long id, String author) {
        Book book = getOne(id);
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Override
    public void modIsbn(Long id, String isbn) {
        Book book = getOne(id);
        book.setIsbn(isbn);
        bookRepository.save(book);
    }

    @Override
    public void modPrice(Long id, Float price) {
        Book book = getOne(id);
        book.setPrice(price);
        bookRepository.save(book);
    }

    @Override
    public void modPub(Long id, String pub) {
        Book book = getOne(id);
        book.setPub(pub);
        bookRepository.save(book);
    }

    @Override
    public void addStock(Long id, Long num) {
        Book book = getOne(id);
        Long cur_stock = book.getStock();
        book.setStock(cur_stock + num);
        bookRepository.save(book);
    }

    @Override
    public void minusStock(Long id, Long num) {
        Book book = getOne(id);
        Long cur_stock = book.getStock();
        book.setStock(cur_stock - num);
        bookRepository.save(book);
    }

    @Override
    public void addSales(Long id, Long num) {
        Book book = getOne(id);
        Long cur_stock = book.getSales();
        book.setStock(cur_stock + num);
        bookRepository.save(book);
    }

    @Override
    public void minusSales(Long id, Long num) {
        Book book = getOne(id);
        Long cur_stock = book.getSales();
        book.setStock(cur_stock - num);
        bookRepository.save(book);
    }

}

