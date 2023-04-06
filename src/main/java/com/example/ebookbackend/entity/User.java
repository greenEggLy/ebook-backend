package com.example.ebookbackend.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties(value = {"adder"})
    @OneToMany(mappedBy = "adder", cascade = CascadeType.ALL)
    private List<CartItem> cart;

    @JsonIgnoreProperties(value = {"buyer"})
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Order> orders;
}
