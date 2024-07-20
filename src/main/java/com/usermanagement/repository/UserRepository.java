package com.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
    Optional<User> findByEmail(String email);
}	
