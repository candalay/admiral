package com.customer.statement.processor;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.customer.statement.config.Constants;
import com.customer.statement.model.CustomerStatement;
import com.customer.statement.model.CustomerStatementRecord;
import com.customer.statement.model.InValidCustomerStatementRecord;
import com.customer.statement.repository.CustomerStatementRepository;
import com.customer.statement.repository.InvalidCustomerStatementRepository;

public class CustomerStatementProcessor implements ItemProcessor<CustomerStatement, InValidCustomerStatementRecord> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerStatementProcessor.class);

	@Inject
	CustomerStatementRepository custStatRepository;

	@Inject
	InvalidCustomerStatementRepository invalidCustStatRepository;

	public InValidCustomerStatementRecord process(CustomerStatement customerStatement) throws Exception {

		LOGGER.info("Processing {}", customerStatement.getReference());
		
		String transactionValidStatus = checkTransaction(customerStatement.getReference(),
				customerStatement.getEndBalance());

		if (StringUtils.isNotBlank(transactionValidStatus)) {
			
			LOGGER.info("invalid transaction {} reason {}", customerStatement.getReference(), transactionValidStatus);

			InValidCustomerStatementRecord invalidRecord = new InValidCustomerStatementRecord();
			invalidRecord.setFailedReason(transactionValidStatus);
			invalidRecord.setAccountNumber(customerStatement.getAccountNumber());
			invalidRecord.setReference(customerStatement.getReference());
			return invalidRecord;
		} else {
			CustomerStatementRecord customerStatementRecord = new CustomerStatementRecord();
			customerStatementRecord.setAccountNumber(customerStatement.getAccountNumber());
			customerStatementRecord.setReference(customerStatement.getReference());
			customerStatementRecord.setDescription(customerStatement.getDescription());
			customerStatementRecord.setEndBalance(customerStatement.getEndBalance());
			customerStatementRecord.setMutation(customerStatement.getMutation());
			customerStatementRecord.setStartBalance(customerStatement.getStartBalance());
			custStatRepository.save(customerStatementRecord);
			
			LOGGER.info("transaction {} is passed", customerStatement.getReference());
		}

		return null;
	}

	private String checkTransaction(BigInteger reference, BigDecimal endBalance) {
		CustomerStatementRecord record = custStatRepository.findCustomerStatementByReference(reference);

		if (record != null) {
			return Constants.REFERENCE_ALREADY_EXISTS;
		}

		if (endBalance.signum() == -1) {
			return Constants.END_BALANCE_NEGATIVE;
		}

		return null;
	}

}
