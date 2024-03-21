package com.luis.pcstore.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    private Validator validator;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        // Inicializa el validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Crea un UserDto de prueba
        userDto = UserDto.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .password("securePassword123")
                .build();
    }

    @Test
    void testUserDtoCreation() {
        assertNotNull(userDto);
        assertEquals("John", userDto.getName());
        assertEquals("Doe", userDto.getSurname());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals("securePassword123", userDto.getPassword());
    }

    @Test
    void testUserDtoValidationSuccess() {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty(), "UserDto should have no validation errors");
    }

    @Test
    void whenEmailIsInvalid_thenValidationFails() {
        userDto.setEmail("invalidEmail");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "UserDto should fail validation due to invalid email");
    }

    @Test
    void whenFieldIsEmpty_thenValidationFails() {
        userDto = new UserDto();
        userDto.setName("");
        userDto.setSurname("Ruiz");
        userDto.setEmail("martina@ejemplo.com");
        userDto.setPassword("securePassword123");
        
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "UserDto should fail validation due to empty name");
    }
}
