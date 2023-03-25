package com.example.ebookbackend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "avatar", length = 2048)
    private String avatar;
    @Column(name = "about")
    private String about;
    @Column(name = "admin")
    private Boolean isAdmin;
    @Column(name = "block")
    private Boolean isBlocked;
    @OneToMany(mappedBy = "adder", cascade = CascadeType.ALL)
    @Column(name = "cart")
    private List<CartItem> cart;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    @Column(name = "orders")
    private List<Order> orders;
}
