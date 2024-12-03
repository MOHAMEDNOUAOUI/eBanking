package com.wora.ebanking.Repository;

import com.wora.ebanking.entities.AUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AUser , Long> {
    Optional<AUser> findAUserByUsername()
}
