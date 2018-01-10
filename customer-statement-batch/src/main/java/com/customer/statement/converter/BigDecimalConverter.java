package com.customer.statement.converter;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalConverter extends XmlAdapter<String, BigDecimal> {

	@Override
	public String marshal(BigDecimal obj) throws Exception {

		return obj.toString();

	}

	@Override
	public BigDecimal unmarshal(String obj) throws Exception {

		return new BigDecimal(obj.replaceAll("\\.", ","));

	}

}
