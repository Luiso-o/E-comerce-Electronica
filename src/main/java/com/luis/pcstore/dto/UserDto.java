package com.luis.pcstore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
