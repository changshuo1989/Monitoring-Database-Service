package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.Rule;
import com.db.service.entity.RuleStatus;

public interface RuleDAO extends JpaRepository<Rule, Integer>{

	@Query(value="SELECT r FROM Rule r WHERE r.ruleStatus = :ruleStatus")
	public List<Rule> findRuleByStatus(@Param("ruleStatus") RuleStatus ruleStatus);
}
