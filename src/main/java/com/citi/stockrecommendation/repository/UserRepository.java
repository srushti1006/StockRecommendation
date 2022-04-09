package com.citi.stockrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.stockrecommendation.dto.Usersecurity;

public interface UserRepository extends JpaRepository<Usersecurity, Long> {
	Usersecurity findByUsername(String username);
}
