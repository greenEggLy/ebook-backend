package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(Long id);

    Book findBookById(Long id);

    void deleteBookById(Long id);

    Book getBookByTitle(String title);

}
