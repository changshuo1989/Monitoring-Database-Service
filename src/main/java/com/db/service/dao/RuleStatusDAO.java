package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.RuleStatus;


public interface RuleStatusDAO extends JpaRepository<RuleStatus, Integer>{
	
	@Query(value = "SELECT rs FROM RuleStatus rs WHERE LOWER(rs.value) = LOWER(:value)")
	public List<RuleStatus> findRuleStatusFromValue(@Param("value") String value);
	
}
