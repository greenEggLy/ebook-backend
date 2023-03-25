package com.example.ebookbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "time")
    private Date time;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Column(name = "items")
    private Set<OrderItem> items;
}
