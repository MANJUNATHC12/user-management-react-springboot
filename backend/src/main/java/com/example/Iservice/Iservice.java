package com.example.Iservice;

import java.util.List;

import com.example.Entity.Users;


public interface Iservice {

	List<Users> searchUsers(String keyword);
	
	List<Users> findbyage(int age);
	
}
