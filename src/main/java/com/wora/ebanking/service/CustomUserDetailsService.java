package com.wora.ebanking.service;

import com.wora.ebanking.entities.AUser;
import com.wora.ebanking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AUser AUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + AUser.getUsername());
        System.out.println("User role: " + AUser.getARole());
        System.out.println("User password: " + AUser.getPassword());

        return new org.springframework.security.core.userdetails.User(
                AUser.getUsername(),
                AUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(AUser.getARole().name()))
        );
    }
}
