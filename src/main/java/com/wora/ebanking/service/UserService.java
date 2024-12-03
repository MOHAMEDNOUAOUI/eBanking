package com.wora.ebanking.service;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.entities.AUser;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    void registerUser(UserDTO userDto);
    UserResponseDTO getAccount();
}
