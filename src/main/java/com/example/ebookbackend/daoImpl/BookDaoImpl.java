package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.repository.BookRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) {
        val book = bookRepository.findBookById(id);
        if (book.getDeleted()) return null;
        else return book;
    }

    @Override
    public Book getOne(Long id) {
        val book = bookRepository.getBookById(id);
        if (book.getDeleted()) return null;
        else return book;
    }

    @Override
    public List<Book> getAll() {
        val all_books = bookRepository.findAll();
        val ret_val = new ArrayList<Book>();
        all_books.forEach(book -> {
            if (!book.getDeleted()) {
                ret_val.add(book);
            }
        });
        return ret_val;
    }


    @Override
    public void deleteBook(Long id) {
        Book book = getOne(id);
        if (book != null) {
            book.setDeleted(true);
            bookRepository.save(book);
        }
    }

    @Override
    public void buyBook(Long id, Long num) throws Exception {
        Book book = getOne(id);
        if (book == null) throw new RuntimeException("该书籍不存在");
        Long cur_stock = book.getStock();
        Long cur_sales = book.getSales();
        if (cur_stock < num) throw new RuntimeException("库存不足");
        book.setStock(cur_stock - num);
        book.setSales(cur_sales + num);
        bookRepository.save(book);
    }

    @Override
    public Book addBook(Book book) {
        bookRepository.save(book);
        Book newbook = bookRepository.getBookByTitle(book.getTitle());
        assert newbook != null;
        return newbook;
    }

    @Override
    public void modBook(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public Book modPic(Long id, String cover) {
        Book book = getOne(id);
        book.setCover(cover);
        bookRepository.save(book);
        return book;
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


}

