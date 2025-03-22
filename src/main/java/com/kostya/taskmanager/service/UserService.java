package com.kostya.taskmanager.service;

import com.kostya.taskmanager.dto.userdto.UserRequestDto;
import com.kostya.taskmanager.dto.userdto.UserResponseDto;
import com.kostya.taskmanager.dto.userdto.UserUpdateDto;
import com.kostya.taskmanager.model.User;
import com.kostya.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserRequestDto userDto;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setId(user.getId());
            responseDto.setUsername(user.getUsername());
            responseDto.setEmail(user.getEmail());
            responseDto.setRole(user.getRole());
            return responseDto;
        }).toList();

    }

    public UserResponseDto createUser(UserRequestDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());

        User saved = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());

        return response;
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!user.getUsername().equals(userUpdateDto.getUsername())) {
            user.setUsername(userUpdateDto.getUsername());
        }
        if(!user.getEmail().equals(userUpdateDto.getEmail())) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if(!user.getRole().equals(userUpdateDto.getRole())) {
            user.setRole(userUpdateDto.getRole());
        }
        User saved = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());

        return response;
    }

    public UserResponseDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        userRepository.delete(user);
        return new UserResponseDto();
    }
}

