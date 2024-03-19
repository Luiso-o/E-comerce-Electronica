package com.luis.pcstore.service;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.CreatedProductDto;
import com.luis.pcstore.dto.ProductDto;
import com.luis.pcstore.helper.ProductsHelper;
import com.luis.pcstore.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.converters.PageableOpenAPIConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {
    @Value("${myapp.image-directory}")
    private String imageDirectoryPath;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductsHelper converter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductsHelper converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    @Override
    public String getUploadDir() {
        return imageDirectoryPath.replace("file:", "");
    }

    @Override
    public Page<ProductDto> showAllProducts(Pageable pageable) {
       Page<Product> productPage = productRepository.findAll(pageable);
       return productPage.map(converter::converterDocumentToDto);
    }

    @Override
    public Page<ProductDto> findProductsByCategory(String category, Pageable pageable) {
        Page<Product> productPageList = productRepository.findByCategory(category, pageable);
        return productPageList.map(converter::converterDocumentToDto);
    }

    @Override
    public void saveProduct(CreatedProductDto createdProductDto, BindingResult result) {
        if (createdProductDto.getImageFileName().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFileName", "The image file is required."));
        }

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product data");
        }

        try {
            String storageFileName = storeImageFile(createdProductDto.getImageFileName());
            Product product = converter.buildProductEntityFromDto(createdProductDto, new Date(), storageFileName);
            productRepository.save(product);
        } catch (IOException e) {
            log.error("Failed to store image file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to store image file");
        }
    }

    private String storeImageFile(MultipartFile image) throws IOException {
        String originalFilename = Objects.requireNonNull(image.getOriginalFilename());
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String storageFileName = UUID.randomUUID() + fileExtension;

        Path uploadPath = Paths.get(getUploadDir());
        ensureDirectoryExists(uploadPath.toString());

        Path filePath = uploadPath.resolve(storageFileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return storageFileName;
    }

    private void ensureDirectoryExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    @Override
    @Transactional
    public void updateProduct(CreatedProductDto productDto, UUID id_product, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Validation errors in ProductDto");
        }

        Product product = findProduct(id_product);
        if (!productDto.getImageFileName().isEmpty()) {
            try {
                updateImageIfNeeded(productDto.getImageFileName(), product);
            } catch (IOException e) {
                log.error("Error updating product image", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating product image");
            }
        }

        updateProductDetails(productDto, product);
    }

    @Override
    public Product findProduct(UUID id_product) {
        return productRepository.findById(id_product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    private void updateImageIfNeeded(MultipartFile newImageFile, Product product) throws IOException {
        if(newImageFile == null || newImageFile.isEmpty()){
            return;
        }
        String storageFileName = storeImageFile(newImageFile);
        deleteImageFile(product.getImageFileName());
        product.setImageFileName(storageFileName);
    }

    private void updateProductDetails(CreatedProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
    }

    public void deleteImageFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) return;
        Path imagePath = Paths.get(getUploadDir(), fileName);
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            log.error("Failed to delete image file: {}", fileName, e);
        }
    }

    @Override
    public void deleteProduct(UUID id_product) {
        productRepository.deleteById(id_product);
    }
}
