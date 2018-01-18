package com.db.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="recipient_types")
public class RecipientType {
	@Id
	private int id;
	
	@Column(nullable=false)
	private String value;
	
	@OneToMany(mappedBy = "recipientType", targetEntity=Recipient.class)
	private List<Recipient> recipients;

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

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}
	
	
}
