package com.wora.ebanking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.relation.Role;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
