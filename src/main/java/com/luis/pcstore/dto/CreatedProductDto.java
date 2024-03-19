package com.luis.pcstore.dto;

import com.luis.pcstore.annotations.FileSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedProductDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The brand is required")
    private String brand;
    @NotEmpty(message = "The category is required")
    private String category;
    @Min(0)
    private double price;
    @Size(min = 10, max = 2000, message = "The description should be at least 10 characters and cannot exceed 2000 characters")
    private String description;
    @FileSize(max = 1048576, message = "The image must not exceed 1MB")
    private MultipartFile imageFileName;
}
