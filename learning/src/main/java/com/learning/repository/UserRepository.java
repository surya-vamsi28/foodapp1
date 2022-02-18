package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Boolean existsByEmail(String email);
	Boolean existsByEmailAndPassword(String email,String password);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	Boolean existsByName(String name);
}
