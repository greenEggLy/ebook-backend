package com.example.ebookbackend.constant.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoForm {
    private Long id;
    private String username;
    private String about;
    private String email;
}
