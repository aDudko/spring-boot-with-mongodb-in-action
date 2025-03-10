package com.dudko.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("items")
public class Item {

    @Id
    UUID id;

    @Field
    String content;

}
