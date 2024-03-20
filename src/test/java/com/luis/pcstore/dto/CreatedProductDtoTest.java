package com.luis.pcstore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatedProductDtoTest {

    private CreatedProductDto product;

    @Mock
    private MultipartFile testImageFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testImageFile = new MockMultipartFile("file", "test-image.jpg", "image/jpeg", "some-image-data".getBytes());

        product = CreatedProductDto.builder()
                .name("Laptop Gamer")
                .brand("BrandX")
                .category("Electronics")
                .price(999.99)
                .description("A powerful gaming laptop")
                .imageFileName(testImageFile)
                .build();
    }

    @Test
    void testProductProperties() {
        assertEquals("Laptop Gamer", product.getName());
        assertEquals("BrandX", product.getBrand());
        assertEquals("Electronics", product.getCategory());
        assertEquals(999.99, product.getPrice());
        assertEquals("A powerful gaming laptop", product.getDescription());
        assertEquals(testImageFile, product.getImageFileName());
    }

    @Test
    void testProductModifications(){
        CreatedProductDto productDto = new CreatedProductDto();
        productDto.setName("Smart TV");
        productDto.setBrand("Samsung");
        productDto.setCategory("TV Home");
        productDto.setPrice(199.11);
        productDto.setDescription("Smart TV Samsung 35''");
        productDto.setImageFileName(testImageFile);

        assertEquals("Smart TV", productDto.getName(), "The name should match the updated value.");
        assertEquals("Samsung", productDto.getBrand(), "The brand should match the updated value.");
        assertEquals("TV Home", productDto.getCategory(), "The category should match the updated value.");
        assertEquals(199.11, productDto.getPrice(), "The price should match the updated value.");
        assertEquals("Smart TV Samsung 35''", productDto.getDescription(), "The description should match the updated value.");
        assertEquals(testImageFile, productDto.getImageFileName(), "The image file name should match the updated value.");
    }

}
