package com.luis.pcstore.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "Products")
public class Product {

    @MongoId
    private UUID id_product;
    private String name;
    private String brand;
    private String category;
    private double price;
    private String description;
    private Date createdAt;
    private String imageFileName;

}
