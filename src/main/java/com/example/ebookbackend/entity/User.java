package com.example.ebookbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "email")
    private String email;
    @Column(name = "is_admin")
    private Boolean is_admin;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private UserAuth userAuth;

    //    @JsonIgnoreProperties(value = {"adder"})
    @JsonIgnore
    @OneToMany(mappedBy = "adder", cascade = CascadeType.ALL)
    private List<CartItem> cart;

    //    @JsonIgnoreProperties(value = {"buyer"})
    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.DETACH)
    private List<Order> orders;
}
