package com.example.ebookbackend.constant.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSalesMoneyForm {
    private String book_name;
    private Float money;
}
