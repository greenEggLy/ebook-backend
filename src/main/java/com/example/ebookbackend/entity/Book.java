package com.example.ebookbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


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
    @Column(name = "stock")
    private Long stock;
    @Column(name = "sales")
    private Long sales;
    @Column(name = "picture", length = 2048)
    private String picture;
    @Column(name = "has_deleted")
    private Boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<CartItem> cartItemSet;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = {CascadeType.DETACH})
    private List<OrderItem> orderItemSet;
}
