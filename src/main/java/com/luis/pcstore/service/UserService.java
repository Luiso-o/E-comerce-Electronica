package com.luis.pcstore.service;

import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;

import java.util.Optional;

public interface UserService {
    void register (UserDto newUser);
    Optional<UserProfileDto> authenticateUser(String email, String password);
}

