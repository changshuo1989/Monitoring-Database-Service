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
@Table(name="checks")
public class Check {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private int sequence;
	
	@Column(nullable=false)
	private String benchmark;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="check_benchmark_id", referencedColumnName="id")
	private CheckBenchmarkType checkBenchmarkType;
	
	
	@Column(nullable=false, name="attribute_name")
	private String attributeName;
	
	@Column(nullable=false,  name="is_active")
	private boolean isActive;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="check_conjunction_id", referencedColumnName="id")
	private CheckConjunctionType checkConjunctionType;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="check_logic_id", referencedColumnName="id")
	private CheckLogicType checkLogicType;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="check_operator_id", referencedColumnName="id")
	private CheckOperatorType checkOperatorType;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="rule_id", referencedColumnName="id")
	private Rule rule;
	
	@Column(nullable=false,name="last_updated", columnDefinition="DATETIME")
	private Date lastUpdated;

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

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public CheckConjunctionType getCheckConjunctionType() {
		return checkConjunctionType;
	}

	public void setCheckConjunctionType(CheckConjunctionType checkConjunctionType) {
		this.checkConjunctionType = checkConjunctionType;
	}

	public CheckLogicType getCheckLogicType() {
		return checkLogicType;
	}

	public void setCheckLogicType(CheckLogicType checkLogicType) {
		this.checkLogicType = checkLogicType;
	}

	public CheckOperatorType getCheckOperatorType() {
		return checkOperatorType;
	}

	public void setCheckOperatorType(CheckOperatorType checkOperatorType) {
		this.checkOperatorType = checkOperatorType;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public CheckBenchmarkType getCheckBenchmarkType() {
		return checkBenchmarkType;
	}

	public void setCheckBenchmarkType(CheckBenchmarkType checkBenchmarkType) {
		this.checkBenchmarkType = checkBenchmarkType;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
