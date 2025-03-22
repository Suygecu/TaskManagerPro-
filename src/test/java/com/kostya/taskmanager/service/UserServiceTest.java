package com.kostya.taskmanager.service;

import com.kostya.taskmanager.dto.userdto.UserRequestDto;
import com.kostya.taskmanager.model.User;
import com.kostya.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void testCreateUserSuccessfully() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserService userService = new UserService(userRepository, passwordEncoder);

        UserRequestDto request = new UserRequestDto();
        request.setUsername("tester");
        request.setEmail("tester@example.com");
        request.setPassword("pass123");
        request.setRole("USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPass");

        User user = new User();
        user.setId(1L);
        user.setUsername("tester");
        user.setEmail("tester@example.com");
        user.setPassword("encodedPass");
        user.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(user);

        var response = userService.createUser(request);
        assertEquals("tester", response.getUsername());
        assertEquals("tester@example.com", response.getEmail());
    }
}