package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.CheckConjunctionType;

public interface CheckConjunctionTypeDAO extends JpaRepository<CheckConjunctionType, Integer>{
	@Query(value = "SELECT cct FROM CheckConjunctionType cct WHERE LOWER(cct.value) = LOWER(:value)")
	public List<CheckConjunctionType> findCheckConjunctionTypeFromValue(@Param("value") String value);
}
