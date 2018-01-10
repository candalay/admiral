package com.customer.statement.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.customer.statement.model.CustomerStatementRecord;

@Repository
public interface CustomerStatementRepository extends BaseRepository<CustomerStatementRecord, Long> {
	
	CustomerStatementRecord findCustomerStatementByReference(BigInteger reference);

}
