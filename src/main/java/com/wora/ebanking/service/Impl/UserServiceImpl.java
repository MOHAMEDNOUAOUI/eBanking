package com.wora.ebanking.service.Impl;

import com.wora.ebanking.DTO.PasswordupdateDTO;
import com.wora.ebanking.DTO.UserDTO;
import com.wora.ebanking.DTO.UserResponseDTO;
import com.wora.ebanking.Exceptions.UsernameAlreadyExistsException;
import com.wora.ebanking.Exceptions.UsernameNotFoundException;
import com.wora.ebanking.Mapper.UserMapper;
import com.wora.ebanking.entities.ARole;
import com.wora.ebanking.entities.AUser;
import com.wora.ebanking.repository.UserRepository;
import com.wora.ebanking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void registerUser(UserDTO userDto) {
        Optional<AUser> AUser = userRepository.findByUsername(userDto.getUsername());
        if (AUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + userDto.getUsername() + " already exists.");
        }

        AUser usersaved = new AUser();
        usersaved.setUsername(userDto.getUsername());
        usersaved.setPassword(passwordEncoder.encode(userDto.getPassword()));
        usersaved.setARole(ARole.ROLE_ADMIN);
        AUser saved = userRepository.save(usersaved);
    }

    @Override
    public UserResponseDTO getAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserResponseDTO getUserByUserName(String userName) {
        AUser AUser = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toDTO(AUser);
    }

    @Override
    public UserResponseDTO updateRole(String username) {
        AUser AUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        AUser.setARole(ARole.ROLE_ADMIN);
        AUser updateUser = userRepository.save(AUser);
        return userMapper.toDTO(updateUser);
    }

    @Override
    public void updatePassword(PasswordupdateDTO passwordupdateDTO) {
        if (!passwordupdateDTO.getPassword().equals(passwordupdateDTO.getPasswordVerify())) {
            throw new RuntimeException("The password doesnt match");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        if (passwordEncoder.matches(passwordupdateDTO.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordupdateDTO.getPassword()));
        }else {
            throw new RuntimeException("The password doesnt match");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }
}
