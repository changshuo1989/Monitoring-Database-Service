package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.ConnectionType;

public interface ConnectionTypeDAO extends JpaRepository<ConnectionType, Integer> {

	@Query(value = "SELECT ct FROM ConnectionType ct WHERE LOWER(ct.value) = LOWER(:value)")
	public List<ConnectionType> findConnectionTypeFromValue(@Param("value") String value);
}
