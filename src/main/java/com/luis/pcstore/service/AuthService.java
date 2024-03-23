package com.luis.pcstore.service;

import com.luis.pcstore.document.User;
import com.luis.pcstore.dto.UserDto;
import com.luis.pcstore.dto.UserProfileDto;
import com.luis.pcstore.exceptions.CustomException;
import com.luis.pcstore.helper.AuthHelper;
import com.luis.pcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthHelper authHelper;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, AuthHelper authHelper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authHelper = authHelper;
    }

    public void register(UserDto request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new CustomException("El email ya está en uso");
        }
        User user = authHelper.convertDotToUser(request);
        userRepository.save(user);
    }

    public UserProfileDto authenticateUser(String email, String password) {
        if(email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new BadCredentialsException("El correo electrónico y la contraseña no pueden estar vacíos");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Autenticación fallida para el usuario: " + email);
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));

        return authHelper.castUserToProfileDto(user);
    }


}