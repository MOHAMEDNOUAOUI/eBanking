package com.wora.ebanking.service;

import com.wora.ebanking.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
}
