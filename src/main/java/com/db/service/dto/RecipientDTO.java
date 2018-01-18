package com.db.service.dto;

import java.util.Date;

import com.db.service.entity.Recipient;
import com.db.service.entity.RecipientType;
import com.db.service.entity.Rule;
import com.db.service.utility.DateTimeAdapter;

public class RecipientDTO {
	private int id;
	
	private int ruleId;
	
	private String target;
	
	private boolean isActive;
	
	private String recipientType;
	
	private String lastUpdated;
	
	private String lastSent;
	
	
	
	public RecipientDTO(){
		
	}
	
	public RecipientDTO(Recipient recipient) throws Exception{
		this.id=recipient.getId();
		this.ruleId=recipient.getRule().getId();
		this.target=recipient.getTarget();
		this.isActive=recipient.getIsActive();
		this.recipientType=recipient.getRecipientType().getValue();
		this.lastUpdated=DateTimeAdapter.fromDateTimeToString(recipient.getLastUpdated());
		this.lastSent=DateTimeAdapter.fromDateTimeToString(recipient.getLastSent());
	}
	
	public Recipient toRecipient(Rule rule, RecipientType rt) throws Exception{
		Recipient rec = new Recipient();
		rec.setTarget(this.getTarget());
		rec.setRecipientType(rt);
		rec.setIsActive(this.isActive);
		rec.setLastUpdated(new Date());
		rec.setLastSent(null);
		rec.setRule(rule);
		return rec;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getLastSent() {
		return lastSent;
	}

	public void setLastSent(String lastSent) {
		this.lastSent = lastSent;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
