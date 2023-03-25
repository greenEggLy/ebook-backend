package com.example.ebookbackend.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Long number;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIncludeProperties(value = {"id", "title", "price"})
    private Book book;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;    // belongs to which order
}
