package com.wora.ebanking.DTO;

import com.wora.ebanking.entities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
