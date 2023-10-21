package com.example.ebookbackend.service.serviceImpl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.service.BookService;
import com.example.ebookbackend.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    final private RedisUtil redis;
    @Autowired
    private BookDao bookDao;

    public BookServiceImpl(RedisUtil redis) {
        this.redis = redis;
    }

    @Override
    public Book findBookById(Long id) {
        if (!redis.isOK()) {
            logger.warn("redis not connected");
            return bookDao.findOne(id);
        }
        String redis_key = String.format("book-%d", id);
        Book redis_res = (Book) redis.get(redis_key);
        if (redis_res == null) {
            Book book = bookDao.findOne(id);
            redis.set(redis_key, book, 60);
            logger.warn("cache not hit: find one book{}", id);
            return book;
        } else {
            logger.info("cache hit: find one book{}", id);
            return redis_res;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        if (!redis.isOK()) {
            logger.warn("redis not connected");
            return bookDao.getAll();
        }
        String redis_key = "book-all";
        List<Book> redis_res = (List<Book>) redis.get(redis_key);
        if (redis_res == null) {
            List<Book> books = bookDao.getAll();
            redis.set(redis_key, books, 60);
            logger.warn("cache not hit: find all books");
            return books;
        } else {
            logger.info("cache hit: find all books");
            return redis_res;
        }
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
        if (!redis.isOK()) {
            logger.warn("redis not connected");
        } else {
            String redis_key = String.format("book-%s", book.getId());
            Book redis_res = (Book) redis.get(redis_key);
            if (redis_res == null) {
                redis.set(redis_key, book, 60);
                logger.warn("cache not hit: modify book{}", book.getId());
            } else {
                redis.set(redis_key, book, 60);
                logger.info("cache hit: modify book{}", book.getId());
            }
        }
        bookDao.modBook(book);
    }

    @Override
    public void addNewBook(String title, String author, String isbn, Float price, String pub, Long stock, String pic_url) {
        Long sales = 0L;
        Book book = Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .price(price)
                .pub(pub)
                .stock(stock)
                .sales(sales)
                .cover(pic_url)
                .deleted(false)
                .build();
        Book newbook = bookDao.addBook(book);
        if (!redis.isOK()) {
            logger.warn("redis not connected");
            return;
        }
        String redis_key = String.format("book-%d", newbook.getId());
        redis.set(redis_key, newbook, 60);
        logger.info("add new book to cache: book{}", newbook.getId());
    }

    @Override
    public void deleteBook(Long id) {
        if (redis.isOK()) {
            logger.warn("redis not connected");
            bookDao.deleteBook(id);
            return;
        }
        String redis_key = String.format("book-%d", id);
        redis.del(redis_key);
        logger.info("delete book{}", id);
        bookDao.deleteBook(id);
    }

    @Override
    public void modPic(Long id, String cover) {
        Book newbook = bookDao.modPic(id, cover);
        if (redis.isOK()) {
            logger.warn("redis not connected");
            return;
        }
        String redis_key = String.format("book-%d", newbook.getId());
        redis.set(redis_key, newbook, 60);
    }

}
