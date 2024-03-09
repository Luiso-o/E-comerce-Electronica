package com.luis.pcstore.service;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.ProductDto;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    String getUploadDir();

    List<Product> showAllProducts ();

    void saveProduct(ProductDto productDto, BindingResult result);

    Product findProduct(UUID id_product);

    void updateProduct(ProductDto productDto, UUID id_product,BindingResult result);

    void deleteProduct(UUID id_product);

}
