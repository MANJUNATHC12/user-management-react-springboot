package com.example.Repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Users;


public interface UserRepositroy extends JpaRepository<Users, Long>{

List<Users> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
	
	List<Users> findByAge(int age);
	
}
