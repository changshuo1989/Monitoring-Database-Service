package com.db.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.service.dao.UserDAO;
import com.db.service.dao.UserTypeDAO;
import com.db.service.dto.UserDTO;
import com.db.service.entity.User;
import com.db.service.entity.UserType;

@Component
public class UserService {

	@Autowired
	UserDAO userRepo;

	@Autowired
	UserTypeDAO userTypeRepo;
	
	
	private UserType getUserTypeByValue(String value) throws Exception{
		List<UserType> userTypes = userTypeRepo.findUserTypeFromValue(value);
		if (userTypes != null && userTypes.size() == 1) {
			return userTypes.get(0);
		}
		else{
			throw new Exception();
		}
	}
	
	public List<UserDTO> getUsers(){
		List<UserDTO> resList = new ArrayList<UserDTO>();
		try{
			List<User> users = userRepo.findAll();
			if(users != null && users.size() > 0){
				for(int i = 0; i< users.size(); i++){
					User user = users.get(i);
					if(user != null){
						resList.add(new UserDTO(user));
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resList;
	}

	
	public UserDTO getUserById(String userIdStr){
		UserDTO userDTO = null;
		try{
			int userId = Integer.parseInt(userIdStr);
			User user = userRepo.findOne(userId);
			if(user != null){
				userDTO = new UserDTO(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userDTO;
	}

	
	public boolean saveUser(UserDTO userDTO) {
		boolean res = false;
		try {
			int id = userDTO.getId();
			if(id <= 0){
				String userTypeValue = userDTO.getUserType();
				UserType userType = getUserTypeByValue(userTypeValue);
					if (userType != null) {
						userRepo.save(userDTO.toUser(userType));
						res = true;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean updateUser(String userIdStr, UserDTO userDTO) {
		boolean res = false;
		try {
			int id = Integer.parseInt(userIdStr);
			User user = userRepo.findOne(id);
			if(user != null){
				String firstname = userDTO.getFirstname();
				String lastname = userDTO.getLastname();
				String username = userDTO.getUsername();
				String email = userDTO.getEmail();
				boolean isActive = userDTO.getIsActive();
				Date lastUpdated = new Date();
				userRepo.updateUserById(firstname, lastname, username, email, isActive, lastUpdated, id);
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;

	}

}
