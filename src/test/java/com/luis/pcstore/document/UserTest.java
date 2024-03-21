package com.luis.pcstore.document;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Validator validator;
    private User user;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        user = new User();
        user.setId_user(UUID.randomUUID());
        user.setName("Juan");
        user.setSurname("Perez");
        user.setEmail("juan@ejemplo.com");
        user.setPassword("securePassword123");
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
        assertNotNull(user.getId_user());
        assertEquals("Juan", user.getName());
        assertEquals("Perez", user.getSurname());
        assertEquals("juan@ejemplo.com", user.getEmail());
        assertEquals("securePassword123", user.getPassword());
    }

    @Test
    void testUserValidation() {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "User should have no validation errors");
    }

    @Test
    void whenEmailIsInvalid_thenValidationFails() {
        user.setEmail("invalidEmail");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "User should fail validation due to invalid email");
    }

    @Test
    void whenAnyFieldIsEmpty_thenValidationFails() {
        User newUser = User.builder()
                .id_user(UUID.randomUUID())
                .name("")
                .surname("Garcia")
                .email("jana@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        assertFalse(violations.isEmpty(), "User should fail validation due to empty name");
    }
}
