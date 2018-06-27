package com.db.service.dto;


import java.util.Date;

import com.db.service.entity.Connection;
import com.db.service.entity.Rule;
import com.db.service.entity.RuleStatus;
import com.db.service.entity.RuleType;
import com.db.service.utility.AESCryptTool;
import com.db.service.utility.DateTimeAdapter;

public class RuleDTO {
	
	private int id;
	
	private int connectionId;
	
	private String name;
	
	private String content;
	
	private String ruleStatus;
	
	private String ruleType;
	
	private String startTime;
	
	private String endTime;
	
	private String lastUpdated;
	
	private String lastTriggered;
	
	private String lastExecuted;
	
	private boolean notifyWhenEmptyData;
	
	public RuleDTO(){}
	
	public RuleDTO(Rule rule) throws Exception{
		String salt = rule.getConnection().getUser().getSalt();
		
		this.id = rule.getId();
		this.connectionId = rule.getConnection().getId();
		this.name = rule.getName();
		this.content = AESCryptTool.decrypt(rule.getContent(), salt);
		this.ruleStatus = rule.getRuleStatus().getValue();
		this.ruleType = rule.getRuleType().getValue();
		this.startTime = DateTimeAdapter.fromDateTimeToString(rule.getStartTime());
		this.endTime = DateTimeAdapter.fromDateTimeToString(rule.getEndTime());
		this.lastUpdated = DateTimeAdapter.fromDateTimeToString(rule.getLastUpdated());
		this.lastTriggered = DateTimeAdapter.fromDateTimeToString(rule.getLastTriggered());
		this.lastExecuted = DateTimeAdapter.fromDateTimeToString(rule.getLastExecuted());
		this.notifyWhenEmptyData = rule.getNotifyWhenEmptyData();
	}
	
	
	public Rule toRule(Connection connection, RuleStatus ruleStatus, RuleType ruleType) throws Exception{
		Rule rule = new Rule();
		String salt = connection.getUser().getSalt();
		rule.setName(this.getName());
		rule.setContent(AESCryptTool.encrypt(this.content, salt));
		rule.setRuleType(ruleType);
		rule.setRuleStatus(ruleStatus);
		rule.setConnection(connection);
		rule.setLastUpdated(new Date());
		rule.setStartTime(DateTimeAdapter.fromStringToDateTime(this.startTime));
		rule.setEndTime(DateTimeAdapter.fromStringToDateTime(this.endTime));
		rule.setNotifyWhenEmptyData(this.notifyWhenEmptyData);
		return rule;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public String getRuleStatus() {
		return ruleStatus;
	}

	public String getRuleType() {
		return ruleType;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getLastTriggered() {
		return lastTriggered;
	}

	public String getLastExecuted() {
		return lastExecuted;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public boolean getNotifyWhenEmptyData() {
		return notifyWhenEmptyData;
	}
	
	
}
