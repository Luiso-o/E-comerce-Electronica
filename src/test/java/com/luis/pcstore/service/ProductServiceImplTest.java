package com.luis.pcstore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.ProductDto;
import com.luis.pcstore.helper.ProductsHelper;
import com.luis.pcstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class ProductServiceImplTest {
    private final UUID testId = UUID.randomUUID();
    private final String testName = "Laptop Gamer";
    private final String testBrand = "BrandX";
    private final String testCategory = "Electronics";
    private final double testPrice = 999.99;
    private final String testDescription = "A powerful gaming laptop";
    private final Date testCreatedAt = new Date();
    private final String testImageFileName = "image.jpg";

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductsHelper converter; // Asumiendo que Converter es tu interfaz/clase de utilidad para la conversión

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, converter); // Asume ProductServiceImpl como tu implementación
    }

    @Test
    void showAllProductsReturnsPageOfProductDto() {
        // Preparación
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(/* parámetros del constructor */));
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findAll(pageable)).thenReturn(productPage);
        when(converter.converterDocumentToDto(any(Product.class))).thenAnswer(i -> new ProductDto(testId, testName, testBrand, testCategory, testPrice, testDescription, testImageFileName, testCreatedAt));

        // Ejecución
        Page<ProductDto> resultPage = productService.showAllProducts(pageable);

        // Verificación
        assertEquals(productList.size(), resultPage.getContent().size(), "El tamaño de la página de ProductDto debe coincidir con el número de Product convertidos.");
        // Aquí podrías añadir más aserciones para verificar, por ejemplo, que los datos del primer elemento coinciden con lo que esperarías dada la entrada simulada.

    }

}