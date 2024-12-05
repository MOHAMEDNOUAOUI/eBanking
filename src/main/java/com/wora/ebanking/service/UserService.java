package com.wora.ebanking.service;

import com.wora.ebanking.DTO.PasswordupdateDTO;
import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.entities.AUser;

import java.util.List;

public interface UserService {
    void registerUser(UserDTO userDto);
    UserResponseDTO getAccount();
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserByUserName(String userName);
    UserResponseDTO updateRole(String username);
    void updatePassword(PasswordupdateDTO passwordupdateDTO);
    void deleteUser(Long id);
}
