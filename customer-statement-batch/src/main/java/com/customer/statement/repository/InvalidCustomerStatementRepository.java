package com.customer.statement.repository;

import org.springframework.stereotype.Repository;

import com.customer.statement.model.InValidCustomerStatementRecord;

@Repository
public interface InvalidCustomerStatementRepository extends BaseRepository<InValidCustomerStatementRecord, Long> {

}
