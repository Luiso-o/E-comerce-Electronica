package com.luis.pcstore.document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.multipart.MultipartFile;

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
