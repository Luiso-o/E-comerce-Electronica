package com.luis.pcstore.service;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.CreatedProductDto;
import com.luis.pcstore.dto.ProductDto;
import org.springdoc.core.converters.PageableOpenAPIConverter;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    String getUploadDir();
    Page<ProductDto> showAllProducts (Pageable pageable);
    Page<ProductDto>findProductsByCategory(String category, Pageable pageable);
    void saveProduct(CreatedProductDto createdProductDto, BindingResult result);
    Product findProduct(UUID id_product);
    void updateProduct(CreatedProductDto productDto, UUID id_product,BindingResult result);
    void deleteProduct(UUID id_product);
}
