package com.example.ebookbackend.constant.common;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddOrderOne {
    public Long user_id;
    public Long book_id;
    public Long num;
}
