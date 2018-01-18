package com.db.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.db.service.entity.ScheduleType;

public interface ScheduleTypeDAO extends JpaRepository<ScheduleType, Integer>{

	@Query(value = "SELECT st FROM ScheduleType st WHERE LOWER(st.value) = LOWER(:value)")
	public List<ScheduleType> findScheduleTypeFromValue(@Param("value") String value);
}
