package com.luis.pcstore.service;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;
import com.luis.pcstore.helper.UserHelper;
import com.luis.pcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserHelper converter;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserHelper converter, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.converter = converter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userInfo) {
        User user = converter.convertDotToDocument(userInfo);
        userRepository.save(user);
    }

    @Override
    public Optional<UserProfileDto> authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if (passwordEncoder.matches(password,user.getPassword())){
                UserProfileDto userProfileDto = converter.castUserToProfileDto(user);
                return Optional.of(userProfileDto);
            }
        }
        return Optional.empty();
    }


}

