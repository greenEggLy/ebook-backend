package com.example.ebookbackend.constant.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSalesForm {
    private String book_name;
    private Long sales;
}
