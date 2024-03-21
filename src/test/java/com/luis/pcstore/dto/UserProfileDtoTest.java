package com.luis.pcstore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class UserProfileDtoTest {

    private UserProfileDto userProfileDto;
    private final UUID testId = UUID.randomUUID();
    private final String testName = "Jane";
    private final String testSurname = "Doe";

    @BeforeEach
    void setUp() {
        userProfileDto = UserProfileDto.builder()
                .id_user(testId)
                .name(testName)
                .surname(testSurname)
                .build();
    }

    @Test
    void testUserProfileDtoCreation() {
        assertNotNull(userProfileDto);
        assertEquals(testId, userProfileDto.getId_user());
        assertEquals(testName, userProfileDto.getName());
        assertEquals(testSurname, userProfileDto.getSurname());
    }

    @Test
    void testUserProfileDtoUpdate() {
        userProfileDto = new UserProfileDto();
        UUID newId = UUID.randomUUID();
        String newName = "John";
        String newSurname = "Smith";

        userProfileDto.setId_user(newId);
        userProfileDto.setName(newName);
        userProfileDto.setSurname(newSurname);

        assertEquals(newId, userProfileDto.getId_user());
        assertEquals(newName, userProfileDto.getName());
        assertEquals(newSurname, userProfileDto.getSurname());
    }
}
