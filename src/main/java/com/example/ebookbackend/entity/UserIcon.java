package com.example.ebookbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "usericon")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIcon {
    @Id
    @Field(name = "_id")
    private int id;

    @Field(name = "_icon")
    private String icon;
}
