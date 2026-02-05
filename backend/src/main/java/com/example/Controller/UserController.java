package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Users;
import com.example.Iservice.Iservice;
import com.example.Repositroy.UserRepositroy;

@RestController

@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserRepositroy repo;
	
	@Autowired
	private Iservice ser;

	
	@GetMapping
	public List<Users> getuser(){
		return repo.findAll();
	}
	
	@PostMapping
	public Users adduser(@RequestBody Users uent) {
		return repo.save(uent);
	}
	
	@PutMapping("/{id}")
	public Users Upadteuser(@PathVariable Long id, @RequestBody Users uent) {
		uent.setId(id);
		return repo.save(uent);
	}
	
	@DeleteMapping("/{id}")
	public void deleteuser(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	
	@GetMapping("/search")
	public List<Users> searchUsers(@RequestParam String keyword){
		return ser.searchUsers(keyword);	
	}
	
	@GetMapping("/byage")
	public List<Users> findByAge(@RequestParam int age){
		return ser.findbyage(age);
	}
	
}
