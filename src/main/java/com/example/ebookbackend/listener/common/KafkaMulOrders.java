package com.example.ebookbackend.listener.common;

import com.example.ebookbackend.constant.common.CliAddOrderMul;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMulOrders {
    public Long user_id;
    public CliAddOrderMul data;
}
