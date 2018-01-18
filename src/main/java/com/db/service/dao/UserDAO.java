package com.db.service.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.db.service.entity.User;


public interface UserDAO extends JpaRepository<User, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname,"
			+ " u.username = :username, u.email = :email, u.isActive = :isActive, " + "u.lastUpdated = :lastUpdated WHERE u.id = :id")
	public void updateUserById(@Param("firstname") String firstname, @Param("lastname") String lastname,
			@Param("username") String username, @Param("email") String email, @Param("isActive") boolean isActive,
			@Param("lastUpdated") Date lastUpdated, @Param("id") int id);

}
