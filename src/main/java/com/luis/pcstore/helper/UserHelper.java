package com.luis.pcstore.helper;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserHelper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User convertDotoToDocument(UserDto user){
        String passwordHash = passwordEncoder.encode(user.getPassword());
        return User.builder()
                .id_user(UUID.randomUUID())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(passwordHash)
                .build();
    }
}

