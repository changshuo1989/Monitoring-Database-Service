package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.CheckOperatorType;

public interface CheckOperatorTypeDAO extends JpaRepository<CheckOperatorType, Integer>{
	@Query(value = "SELECT cot FROM CheckOperatorType cot WHERE LOWER(cot.value) = LOWER(:value)")
	public List<CheckOperatorType> findCheckOperatorTypeFromValue(@Param("value") String value);
}
