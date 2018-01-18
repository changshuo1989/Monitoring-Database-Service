package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.RuleType;


public interface RuleTypeDAO extends JpaRepository<RuleType, Integer>{
	
	@Query(value = "SELECT rt FROM RuleType rt WHERE LOWER(rt.value) = LOWER(:value)")
	public List<RuleType> findRuleTypeFromValue(@Param("value") String value);
}
