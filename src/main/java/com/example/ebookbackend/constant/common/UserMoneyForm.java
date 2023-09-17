package com.example.ebookbackend.constant.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMoneyForm {
    Long id;
    String username;
    Double money;
}
