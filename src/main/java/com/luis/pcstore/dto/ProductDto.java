package com.luis.pcstore.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    //testeado
    private UUID id_product;
    private String name;
    private String brand;
    private String category;
    private double price;
    private String description;
    private String imageFileName;
    private Date createdAt;
}
