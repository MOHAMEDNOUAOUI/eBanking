package com.wora.ebanking.service;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.Exceptions.UsernameAlreadyExistsException;
import com.wora.ebanking.Mapper.UserMapper;
import com.wora.ebanking.entities.User;
import com.wora.ebanking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User registerUser(UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).
                orElseThrow(() -> new UsernameAlreadyExistsException("Username " + userDto.getUsername() + " already exists."));

        User usersaved = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }
}
