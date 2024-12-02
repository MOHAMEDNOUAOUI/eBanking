package com.wora.ebanking.controller;


import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }
}
