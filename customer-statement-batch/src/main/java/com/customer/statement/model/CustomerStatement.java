package com.customer.statement.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
public class CustomerStatement {
	
	private BigInteger reference;
	private String accountNumber;
	private String description;
	private BigDecimal startBalance;
	private String mutation;
	private BigDecimal endBalance;
	
	@XmlAttribute(name = "reference")
	public BigInteger getReference() {
		return reference;
	}
	public void setReference(BigInteger reference) {
		this.reference = reference;
	}
	
	@XmlElement(name = "accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}
	public String getMutation() {
		return mutation;
	}
	public void setMutation(String mutation) {
		this.mutation = mutation;
	}
	public BigDecimal getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

}
