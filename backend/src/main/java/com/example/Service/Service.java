package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Entity.Users;
import com.example.Iservice.Iservice;
import com.example.Repositroy.UserRepositroy;

@org.springframework.stereotype.Service
public class Service implements Iservice{

	
	
	@Autowired
	private UserRepositroy repo;

	@Override
	public List<Users> searchUsers(String keyword) {
		// TODO Auto-generated method stub
		return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
	}

	@Override
	public List<Users> findbyage(int age) {
		// TODO Auto-generated method stub
		return repo.findByAge(age);
	}
	
	
	
}
