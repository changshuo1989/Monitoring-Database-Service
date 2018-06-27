package com.db.service.dto;

import java.util.List;


public class ExecutionServiceRuleDTO {
	
	private int id;
	private String name;
	private String content;
	private String ruleStatus;
	private String ruleType;
	private boolean notifyWhenEmptyData;
	private ConnectionDTO connection;
	private List<CheckDTO> checks;
	private List<RecipientDTO> recipients;
	
	
	
	public ExecutionServiceRuleDTO(RuleDTO rule, ConnectionDTO conn, List<CheckDTO> checks, 
			List<RecipientDTO> recipients){
		this.id = rule.getId();
		this.name = rule.getName();
		this.content = rule.getContent();
		this.ruleStatus = rule.getRuleStatus();
		this.ruleType = rule.getRuleType();
		this.notifyWhenEmptyData = rule.getNotifyWhenEmptyData();
		this.connection = conn;
		this.checks = checks;
		this.recipients = recipients;
	}
	
	
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
	public String getRuleStatus() {
		return ruleStatus;
	}
	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public ConnectionDTO getConnection() {
		return connection;
	}
	public void setConnection(ConnectionDTO connection) {
		this.connection = connection;
	}
	public List<CheckDTO> getChecks() {
		return checks;
	}
	public void setChecks(List<CheckDTO> checks) {
		this.checks = checks;
	}
	public List<RecipientDTO> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<RecipientDTO> recipients) {
		this.recipients = recipients;
	}


	public boolean getNotifyWhenEmptyData() {
		return notifyWhenEmptyData;
	}


	public void setNotifyWhenEmptyData(boolean notifyWhenEmptyData) {
		this.notifyWhenEmptyData = notifyWhenEmptyData;
	}
	
	
	
}
