package com.luis.pcstore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {

    private ProductDto productDto;
    private final UUID testId = UUID.randomUUID();
    private final String testName = "Laptop Gamer";
    private final String testBrand = "BrandX";
    private final String testCategory = "Electronics";
    private final double testPrice = 999.99;
    private final String testDescription = "A powerful gaming laptop";
    private final String testImageFileName = "image.jpg";
    private final Date testCreatedAt = new Date();

    @BeforeEach
    void setUp() {
        productDto = new ProductDto();
        productDto.setId_product(testId);
        productDto.setName(testName);
        productDto.setBrand(testBrand);
        productDto.setCategory(testCategory);
        productDto.setPrice(testPrice);
        productDto.setDescription(testDescription);
        productDto.setImageFileName(testImageFileName);
        productDto.setCreatedAt(testCreatedAt);

    }

    @Test
    void testProductDtoCreation() {
        assertNotNull(productDto);
        assertEquals(testId, productDto.getId_product());
        assertEquals(testName, productDto.getName());
        assertEquals(testBrand, productDto.getBrand());
        assertEquals(testCategory, productDto.getCategory());
        assertEquals(testPrice, productDto.getPrice());
        assertEquals(testDescription, productDto.getDescription());
        assertEquals(testImageFileName, productDto.getImageFileName());
        assertEquals(testCreatedAt, productDto.getCreatedAt());
    }

    @Test
    void testProductDtoUpdate() {
        productDto = ProductDto.builder()
                .id_product(testId)
                .name(testName)
                .brand(testBrand)
                .category(testCategory)
                .price(testPrice)
                .description(testDescription)
                .imageFileName(testImageFileName)
                .createdAt(testCreatedAt)
                .build();

        // Cambia un campo para probar la actualización
        String newDescription = "An updated description";
        productDto.setDescription(newDescription);

        assertEquals(newDescription, productDto.getDescription());
    }
}
