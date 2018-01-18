package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.CheckBenchmarkType;

public interface CheckBenchmarkTypeDAO extends JpaRepository<CheckBenchmarkType, Integer>{
	@Query(value = "SELECT cbt FROM CheckBenchmarkType cbt WHERE LOWER(cbt.value) = LOWER(:value)")
	public List<CheckBenchmarkType> findCheckBenchmarkTypeFromValue(@Param("value") String value);
}
