package com.example.ebookbackend.constant.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManUserInfoForm {
    Long id;
    String username;
    String email;
    Boolean is_admin;
    Boolean is_blocked;
}
