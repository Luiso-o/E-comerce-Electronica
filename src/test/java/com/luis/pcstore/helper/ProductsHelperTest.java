package com.luis.pcstore.helper;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.CreatedProductDto;
import com.luis.pcstore.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsHelperTest {

    private ProductsHelper productsHelper;
    private CreatedProductDto createdProductDto;
    private Product product;

    private final String testName = "Laptop Ultra";
    private final String testBrand = "BrandY";
    private final String testCategory = "Electronics";
    private final double testPrice = 1500.00;
    private final String testDescription = "An ultra-performance laptop";
    private final String testImageFileName = "laptop-ultra.jpg";
    private Date testCreatedAt;

    @BeforeEach
    void setUp() {
        productsHelper = new ProductsHelper();

        product = Product.builder()
                .id_product(UUID.randomUUID())
                .name(testName)
                .brand(testBrand)
                .category(testCategory)
                .price(testPrice)
                .imageFileName(testImageFileName)
                .createdAt(new Date())
                .build();


        testCreatedAt = new Date();

        createdProductDto = CreatedProductDto.builder()
                .name(testName)
                .brand(testBrand)
                .category(testCategory)
                .price(testPrice)
                .description(testDescription)
                .build();
    }

    @Test
    void converterDocumentToDtoTest() {
        ProductDto productDto = productsHelper.converterDocumentToDto(product);

        // Verificar que todos los campos se han copiado correctamente
        assertEquals(product.getId_product(), productDto.getId_product());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getBrand(), productDto.getBrand());
        assertEquals(product.getCategory(), productDto.getCategory());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getImageFileName(), productDto.getImageFileName());
        assertEquals(product.getCreatedAt(), productDto.getCreatedAt());
    }

    @Test
    void buildProductEntityFromDtoTest() {
        Product product = productsHelper.buildProductEntityFromDto(createdProductDto, testCreatedAt, testImageFileName);

        assertNotNull(product.getId_product()); // The ID should be auto-generated and not null
        assertEquals(testName, product.getName());
        assertEquals(testBrand, product.getBrand());
        assertEquals(testCategory, product.getCategory());
        assertEquals(testPrice, product.getPrice());
        assertEquals(testDescription, product.getDescription());
        assertEquals(testCreatedAt, product.getCreatedAt());
        assertEquals(testImageFileName, product.getImageFileName());
    }
}
