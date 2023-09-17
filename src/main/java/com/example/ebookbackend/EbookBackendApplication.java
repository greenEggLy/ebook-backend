package com.example.ebookbackend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class EbookBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbookBackendApplication.class, args);
    }


    @Bean
    public NewTopic mulOrderTopic() {
        return TopicBuilder.name("mul-orders")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic oneOrderTopic() {
        return TopicBuilder.name("one-order")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
