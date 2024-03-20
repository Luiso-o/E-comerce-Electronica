package com.luis.pcstore.service;

import com.luis.pcstore.dto.UserDto;

public interface UserService {
    void register (UserDto newUser);
    boolean authenticateUser(String email, String password);
}

