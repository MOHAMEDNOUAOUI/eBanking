package com.wora.ebanking.Service.impl;


import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void register(UserDTO userDTO) {

    }

    @Override
    public UserResponseDTO getAccount() {
        return null;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return null;
    }
}
