package com.db.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.service.entity.Schedule;


public interface ScheduleDAO extends JpaRepository<Schedule, Integer>{

}
