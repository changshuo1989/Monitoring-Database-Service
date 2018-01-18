package com.db.service.dto;

import java.util.Date;

import com.db.service.entity.Check;
import com.db.service.entity.CheckBenchmarkType;
import com.db.service.entity.CheckConjunctionType;
import com.db.service.entity.CheckLogicType;
import com.db.service.entity.CheckOperatorType;
import com.db.service.entity.Rule;
import com.db.service.utility.DateTimeAdapter;

public class CheckDTO {
	private int id;
	private int ruleId;
	private int sequence;
	private String benchmark;
	private String checkBenchmarkType;
	private String attributeName;
	private boolean isActive;
	private String checkConjunctionType;
	private String checkLogicType;
	private String checkOperatorType;
	private String lastUpdated;
	
	public CheckDTO() {}
	
	public CheckDTO(Check check){
		this.id = check.getId();
		this.ruleId = check.getRule().getId();
		this.sequence = check.getSequence();
		this.benchmark = check.getBenchmark();
		this.checkBenchmarkType = check.getCheckBenchmarkType().getValue();
		this.attributeName = check.getAttributeName();
		this.isActive = check.getIsActive();
		this.checkConjunctionType = check.getCheckConjunctionType().getValue();
		this.checkLogicType = check.getCheckLogicType().getValue();
		this.checkOperatorType = check.getCheckOperatorType().getValue();
		this.lastUpdated = DateTimeAdapter.fromDateTimeToString(check.getLastUpdated());
		
	}
	
	public Check toCheck(Rule rule, CheckBenchmarkType checkBenchmarkType, CheckConjunctionType checkConjunctionType, 
			CheckLogicType checkLogicType, CheckOperatorType checkOperatorType){
		
		Check check = new Check();
		check.setSequence(this.getSequence());
		check.setBenchmark(this.getBenchmark());
		check.setCheckBenchmarkType(checkBenchmarkType);
		check.setAttributeName(this.getAttributeName());
		check.setIsActive(this.isActive);
		check.setCheckConjunctionType(checkConjunctionType);
		check.setCheckLogicType(checkLogicType);
		check.setCheckOperatorType(checkOperatorType);
		check.setLastUpdated(new Date());
		check.setRule(rule);
		
		return check;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	public String getCheckBenchmarkType() {
		return checkBenchmarkType;
	}
	public void setCheckBenchmarkType(String checkBenchmarkType) {
		this.checkBenchmarkType = checkBenchmarkType;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCheckConjunctionType() {
		return checkConjunctionType;
	}
	public void setCheckConjunctionType(String checkConjunctionType) {
		this.checkConjunctionType = checkConjunctionType;
	}
	public String getCheckLogicType() {
		return checkLogicType;
	}
	public void setCheckLogicType(String checkLogicType) {
		this.checkLogicType = checkLogicType;
	}
	public String getCheckOperatorType() {
		return checkOperatorType;
	}
	public void setCheckOperatorType(String checkOperatorType) {
		this.checkOperatorType = checkOperatorType;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	

}
