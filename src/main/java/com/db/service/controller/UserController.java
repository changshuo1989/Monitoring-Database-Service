package com.db.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.service.dto.UserDTO;
import com.db.service.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<UserDTO> getUsers(){
		return userService.getUsers();
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.GET)
	public UserDTO getUsers(@PathVariable("user_id") String userIdStr){
		return userService.getUserById(userIdStr);
	}
	
	@CrossOrigin
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public boolean saveUser(@RequestBody(required=false) UserDTO userDTO){
		return userService.saveUser(userDTO);
	}
	
	@CrossOrigin
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.PUT)
	public boolean updateUser(@PathVariable("user_id") String userIdStr, @RequestBody(required=false) UserDTO userDTO){
		return userService.updateUser(userIdStr, userDTO);
	}
}
