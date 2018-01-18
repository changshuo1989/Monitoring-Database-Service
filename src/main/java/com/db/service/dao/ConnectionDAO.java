package com.db.service.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.db.service.entity.Connection;

public interface ConnectionDAO extends JpaRepository<Connection, Integer>{
	
	@Modifying
	@Transactional
	@Query(value="UPDATE Connection c SET c.name = :name, c.host=:host, "
			+ "c.port = :port, c.db = :db, c.username = :username, c.password = :password,"
			+ "c.isActive = :isActive, c.lastUpdated = :lastUpdated WHERE c.id = :id ")
	public void updateConnectionById(@Param("name") String name, @Param("host") String host,
			@Param("port") String port, @Param("db") String db, @Param("username") String username,
			@Param("password") String password, @Param("isActive") boolean isActive, @Param("lastUpdated") Date lastUpdated, @Param("id") int id);
}
