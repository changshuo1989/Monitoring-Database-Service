package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.CheckLogicType;

public interface CheckLogicTypeDAO extends JpaRepository<CheckLogicType, Integer>{
	@Query(value = "SELECT clt FROM CheckLogicType clt WHERE LOWER(clt.value) = LOWER(:value)")
	public List<CheckLogicType> findCheckLogicTypeFromValue (@Param("value") String value);
}
