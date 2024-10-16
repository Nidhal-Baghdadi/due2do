package com.example.due2do.repository;

import com.example.due2do.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsername(String username);
}

