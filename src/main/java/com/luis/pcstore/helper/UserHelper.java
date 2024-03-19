package com.luis.pcstore.helper;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserHelper {

    public User convertDotoToDocument(UserDto user){
        return User.builder()
                .id_user(UUID.randomUUID())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .build();
    }

}

