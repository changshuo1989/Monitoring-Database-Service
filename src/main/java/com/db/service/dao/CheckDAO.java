package com.db.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.service.entity.Check;

public interface CheckDAO extends JpaRepository<Check, Integer>{

}
