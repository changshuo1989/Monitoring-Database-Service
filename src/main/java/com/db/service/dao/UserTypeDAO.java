package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.UserType;

public interface UserTypeDAO extends JpaRepository<UserType,Integer>{
	
	@Query(value = "SELECT ut FROM UserType ut WHERE LOWER(ut.value) = LOWER(:value)" )
	public List<UserType> findUserTypeFromValue(@Param("value") String value);
	
}
