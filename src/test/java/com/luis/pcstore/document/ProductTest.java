package com.luis.pcstore.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private final UUID testId = UUID.randomUUID();
    private final String testName = "Laptop Gamer";
    private final String testBrand = "BrandX";
    private final String testCategory = "Electronics";
    private final double testPrice = 999.99;
    private final String testDescription = "A powerful gaming laptop";
    private final Date testCreatedAt = new Date();
    private final String testImageFileName = "image.jpg";

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id_product(testId)
                .name(testName)
                .brand(testBrand)
                .category(testCategory)
                .price(testPrice)
                .description(testDescription)
                .createdAt(testCreatedAt)
                .imageFileName(testImageFileName)
                .build();
    }

    @Test
    void testProductCreation() {
        assertNotNull(product);
        assertEquals(testId, product.getId_product());
        assertEquals(testName, product.getName());
        assertEquals(testBrand, product.getBrand());
        assertEquals(testCategory, product.getCategory());
        assertEquals(testPrice, product.getPrice());
        assertEquals(testDescription, product.getDescription());
        assertEquals(testCreatedAt, product.getCreatedAt());
        assertEquals(testImageFileName, product.getImageFileName());
    }

    @Test
    void DataProductTest(){
        Product emptyProduct = new Product();
        emptyProduct.setId_product(UUID.randomUUID());
        emptyProduct.setName("Smart TV");
        emptyProduct.setBrand("Samsung");
        emptyProduct.setCategory("TV Home");
        emptyProduct.setPrice(199.11);
        emptyProduct.setDescription("Smart TV Samsung 35''");
        emptyProduct.setCreatedAt(new Date());
        emptyProduct.setImageFileName("TvImage.png");
    }
}