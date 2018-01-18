package com.db.service.dto;
import java.util.Date;
import com.db.service.entity.User;
import com.db.service.entity.UserType;
import com.db.service.utility.AESCryptTool;
import com.db.service.utility.BCryptTool;
import com.db.service.utility.DateTimeAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;




@JsonInclude(Include.NON_NULL)
public class UserDTO {
	
	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private boolean isActive;
	
	private String lastUpdated;
	
	private String userType;
	
	
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.id= user.getId();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.isActive = user.getIsActive();
		this.lastUpdated = DateTimeAdapter.fromDateTimeToString(user.getLastUpdated());
		this.userType = user.getUserType().getValue();
	}
	
	public User toUser(UserType ut) throws Exception{
		
		User user = new User();
		user.setFirstname(this.firstname);
		user.setLastname(this.lastname);
		user.setUsername(this.username);
		if(this.password!= null && !this.password.equals("")){
			String salt = AESCryptTool.generateSalt();
			user.setSalt(salt);
			user.setPassword(BCryptTool.encrypt(this.password));
		}
		user.setEmail(this.email);
		user.setIsActive(this.isActive);
		user.setLastUpdated(new Date());
		user.setUserType(ut);
		return user;
	}

	public int getId() {
		return id;
	}


	public String getFirstname() {
		return firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public String getUsername() {
		return username;
	}


	public boolean getIsActive() {
		return isActive;
	}


	public String getLastUpdated() {
		return lastUpdated;
	}


	public String getUserType() {
		return userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

	
}
