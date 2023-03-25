package com.example.ebookbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "price")
    private Float price;
    @Column(name = "pub")
    private String pub;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Column(name = "pics")
    private Set<Picture> pics;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "sales")
    private Long sales;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Column(name = "belong_cart")
    private Set<CartItem> cartItemSet;
    @OneToMany(mappedBy = "book", cascade = {CascadeType.DETACH})
    @Column(name = "belong_order")
    private Set<OrderItem> orderItemSet;
}
