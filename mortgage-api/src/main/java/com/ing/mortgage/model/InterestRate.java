package com.ing.mortgage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_interest_rates")
public class InterestRate {

	@Id
	@Column(name = "interest_rate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "maturity_period")
	private Integer maturityPeriod;

	@Column(name = "interest_rate")
	private Double rate;

	@Column(name = "last_updated_date")
	private Date lastUpdateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(Integer maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}
	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
