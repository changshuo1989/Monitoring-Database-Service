package com.db.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="schedules")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(nullable=false)
	private int date;
	
	@Column(nullable=false,  name="is_active")
	private boolean isActive;
	
	@Column(nullable=false, name="hour_of_day")
	private int hourOfDay;
	@Column(nullable=false, name="repeat_every")
	private int repeatEvery;
	@Column(nullable=false, name="last_updated", columnDefinition="DATETIME")
	private Date lastUpdated;
	@Column(nullable=true, name="last_triggered", columnDefinition="DATETIME")
	private Date lastTriggered;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="type_id", referencedColumnName="id")
	private ScheduleType scheduleType;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="rule_id", referencedColumnName="id")
	private Rule rule;
	
	/*
	@OneToMany(mappedBy="schedule", targetEntity=Recipient.class)
	private List<Recipient> recipients;
	*/

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

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getLastTriggered() {
		return lastTriggered;
	}

	public void setLastTriggered(Date lastTriggered) {
		this.lastTriggered = lastTriggered;
	}

	public ScheduleType getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
/*
	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}
	
*/	
	
}
