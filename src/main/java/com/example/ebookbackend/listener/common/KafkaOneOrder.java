package com.example.ebookbackend.listener.common;

import com.example.ebookbackend.constant.common.CliAddOrderOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOneOrder {
    public Long user_id;
    public CliAddOrderOne data;
}
