package com.luis.pcstore.service;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.helper.UserHelper;
import com.luis.pcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserHelper converter;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserHelper converter, UserRepository userRepository) {
        this.converter = converter;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserDto userInfo) {
        User user = converter.convertDotoToDocument(userInfo);
        userRepository.save(user);
    }
}

