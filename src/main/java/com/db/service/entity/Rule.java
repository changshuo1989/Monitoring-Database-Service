package com.db.service.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rules")
public class Rule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String content;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="status_id", referencedColumnName="id")
	private RuleStatus ruleStatus;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="type_id", referencedColumnName="id")
	private RuleType ruleType;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="connection_id", referencedColumnName="id")
	private Connection connection;
	
	@OneToMany(mappedBy="rule", targetEntity=Schedule.class)
	private List<Schedule> schedules;
	
	@OneToMany(mappedBy="rule", targetEntity=Check.class)
	private List<Check> checks;
	
	@OneToMany(mappedBy="rule", targetEntity=Recipient.class)
	private List<Recipient> recipients;
	
	@Column(nullable=true, name="start_time", columnDefinition="DATETIME")
	private Date startTime;
	
	@Column(nullable=true, name="end_time", columnDefinition="DATETIME")
	private Date endTime;
	
	@Column(nullable=false, name="last_updated", columnDefinition="DATETIME")
	private Date lastUpdated;
	
	@Column(nullable=true, name="last_triggered", columnDefinition="DATETIME")
	private Date lastTriggered;
	
	@Column(nullable=true, name="last_executed", columnDefinition="DATETIME")
	private Date lastExecuted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RuleStatus getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(RuleStatus ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Date getLastExecuted() {
		return lastExecuted;
	}

	public void setLastExecuted(Date lastExecuted) {
		this.lastExecuted = lastExecuted;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}
	
	
	
	
}
