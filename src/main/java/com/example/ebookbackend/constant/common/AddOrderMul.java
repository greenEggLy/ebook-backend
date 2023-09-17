package com.example.ebookbackend.constant.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderMul {
    public Long user_id;
    public List<Long> item_id;

    @Override
    public String toString() {
        return "AddOrderMul{" +
                "user_id=" + user_id +
                ", item_id=" + item_id +
                '}';
    }
}
