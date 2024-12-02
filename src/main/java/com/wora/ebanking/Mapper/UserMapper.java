package com.wora.ebanking.Mapper;

import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.entities.AUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AUser toEntity(UserDTO dto);
//    @Mapping(source ="aRole" , target = "aRole")
    UserResponseDTO toDTO(AUser AUser);
}
