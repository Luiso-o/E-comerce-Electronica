package com.luis.pcstore.helper;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.CreatedProductDto;
import com.luis.pcstore.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class ProductsHelper {

    //testeado
    public ProductDto converterDocumentToDto(Product product){
        return ProductDto.builder()
                .id_product(product.getId_product())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory())
                .price(product.getPrice())
                .imageFileName(product.getImageFileName())
                .createdAt(product.getCreatedAt())
                .build();
    }

    //testeado
    public Product buildProductEntityFromDto(CreatedProductDto createdProductDto, Date createAt, String imageFileName) {
        return Product.builder()
                .id_product(UUID.randomUUID())
                .name(createdProductDto.getName())
                .brand(createdProductDto.getBrand())
                .category(createdProductDto.getCategory())
                .price(createdProductDto.getPrice())
                .description(createdProductDto.getDescription())
                .createdAt(createAt)
                .imageFileName(imageFileName)
                .build();
    }

}
