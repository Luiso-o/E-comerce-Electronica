package com.luis.pcstore.helper;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthHelper {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertDotToUser(UserDto user){
        return User.builder()
                .id_user(UUID.randomUUID())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
    }

    public UserProfileDto castUserToProfileDto(User user){
        return UserProfileDto.builder()
                .id_user(user.getId_user())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}

