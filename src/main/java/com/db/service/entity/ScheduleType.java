package com.db.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="schedule_types")
public class ScheduleType {
	@Id
	private int id;
	
	@Column(nullable=false)
	private String value;
	
	@OneToMany(mappedBy = "scheduleType", targetEntity=Schedule.class)
	private List<Schedule> schedules;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	
}
