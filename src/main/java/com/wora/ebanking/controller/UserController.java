package com.wora.ebanking.controller;


import com.wora.ebanking.DTO.PasswordupdateDTO;
import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.entities.AUser;
import com.wora.ebanking.service.Impl.UserServiceImpl;
import com.wora.ebanking.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }

    @GetMapping("/contact")
    public String getContactInfo() {
        return "Contact Info";
    }

    @GetMapping("/myLoans")
    public String getLoan() {return  "MyLoan";};

    @GetMapping("/myCards")
    public String getCards() {return  "Cards list" ;};

    @GetMapping("/myAccount")
    public ResponseEntity<UserResponseDTO> getAccount() {
        return ResponseEntity.ok(userService.getAccount());
    }

    @GetMapping("/myBalance")
    public String getBalance() {return "Balance is 100DH" ;};

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(   ) {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable("username") String username ) {
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }

    @PutMapping("/users/{username}/updateRole")
    public ResponseEntity<UserResponseDTO> updateRole(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.updateRole(username));
    }

    @PutMapping("/users/passwordChanger")
    public ResponseEntity<String> changePassword(@RequestBody PasswordupdateDTO passwordupdateDTO) {
        userService.updatePassword(passwordupdateDTO);
        return ResponseEntity.ok("Password changed successfully!");
    }


}
