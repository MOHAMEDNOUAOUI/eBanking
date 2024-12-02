package com.wora.ebanking.service.Impl;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.Exceptions.UsernameAlreadyExistsException;
import com.wora.ebanking.Mapper.UserMapper;
import com.wora.ebanking.entities.ARole;
import com.wora.ebanking.entities.AUser;
import com.wora.ebanking.repository.UserRepository;
import com.wora.ebanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AUser registerUser(UserDTO userDto) {
        Optional<AUser> AUser = userRepository.findByUsername(userDto.getUsername());
        if (AUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + userDto.getUsername() + " already exists.");
        }

        AUser usersaved = new AUser();
        usersaved.setUsername(userDto.getUsername());
        usersaved.setPassword(passwordEncoder.encode(userDto.getPassword()));
        usersaved.setARole(ARole.ROLE_USER);
        return userRepository.save(usersaved);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
}
