package com.db.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.db.service.entity.Recipient;

public interface RecipientDAO extends JpaRepository<Recipient, Integer>{

}
