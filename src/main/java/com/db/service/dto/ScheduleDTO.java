package com.db.service.dto;

import java.util.Date;

import com.db.service.entity.Rule;
import com.db.service.entity.Schedule;
import com.db.service.entity.ScheduleType;
import com.db.service.utility.DateTimeAdapter;

public class ScheduleDTO {
	
	private int id;
	
	private int ruleId;
	
	private int date;
	
	private boolean isActive;
	
	private int hourOfDay;
	
	private int repeatEvery;
	
	private String lastUpdated;
	
	private String lastTriggered;
	
	private String scheduleType;
	
	public ScheduleDTO(){}
	
	public ScheduleDTO(Schedule schedule) throws Exception{
		this.id = schedule.getId();
		this.ruleId = schedule.getRule().getId();
		this.date=schedule.getDate();
		this.isActive=schedule.getIsActive();
		this.hourOfDay=schedule.getHourOfDay();
		this.repeatEvery=schedule.getRepeatEvery();
		this.lastUpdated=DateTimeAdapter.fromDateTimeToString(schedule.getLastUpdated());
		this.lastTriggered=DateTimeAdapter.fromDateTimeToString(schedule.getLastTriggered());
		this.scheduleType=schedule.getScheduleType().getValue();
	}
	
	public Schedule toSchedule(Rule rule, ScheduleType st) throws Exception{
		Schedule schedule = new Schedule();
		schedule.setDate(this.date);
		schedule.setIsActive(this.isActive);
		schedule.setHourOfDay(this.getHourOfDay());
		schedule.setRepeatEvery(this.getRepeatEvery());
		schedule.setLastUpdated(new Date());
		schedule.setScheduleType(st);
		schedule.setLastTriggered(null);
		schedule.setRule(rule);
		return schedule;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getHourOfDay() {
		return hourOfDay;
	}
	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}
	public int getRepeatEvery() {
		return repeatEvery;
	}
	public void setRepeatEvery(int repeatEvery) {
		this.repeatEvery = repeatEvery;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getLastTriggered() {
		return lastTriggered;
	}
	public void setLastTriggered(String lastTriggered) {
		this.lastTriggered = lastTriggered;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	

}
