package com.wora.ebanking.Mapper;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    @Mapping(source ="role" , target = "role")
    UserResponseDTO toDTO(User user);
}
