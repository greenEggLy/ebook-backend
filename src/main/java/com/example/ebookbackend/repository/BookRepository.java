package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(Long id);

    Book findBookById(Long id);

    void deleteBookById(Long id);

}
