package com.db.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="check_benchmark_types")
public class CheckBenchmarkType {
	@Id
	private int id;
	
	@Column(nullable=false)
	private String value;
	
	@OneToMany(mappedBy = "checkBenchmarkType", targetEntity=Check.class)
	private List<Check> checks;

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

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}
	
}
