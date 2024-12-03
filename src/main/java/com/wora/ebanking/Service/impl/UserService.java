package com.wora.ebanking.Service.impl;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    void register(UserDTO userDTO);
    UserResponseDTO getAccount();
    List<UserResponseDTO> getUsers();
    void deleteUser(Long id);
    UserResponseDTO getUser(Long id);
}
