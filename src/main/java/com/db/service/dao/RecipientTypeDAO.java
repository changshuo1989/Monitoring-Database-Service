package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.db.service.entity.RecipientType;

public interface RecipientTypeDAO extends JpaRepository<RecipientType,Integer>{

	@Query(value = "SELECT rt FROM RecipientType rt WHERE LOWER(rt.value) = LOWER(:value)")
	public List<RecipientType> findRecipientTypeFromValue(@Param("value") String value);
}
