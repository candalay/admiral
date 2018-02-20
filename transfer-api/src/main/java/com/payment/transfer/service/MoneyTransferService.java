package com.payment.transfer.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;
import com.payment.transfer.handler.AccountHandler;
import com.payment.transfer.handler.TransferHandler;
import com.payment.transfer.validate.ServiceValidator;

@Transactional
@Service("MoneyTransferService")
public class MoneyTransferService implements TransferService {
	
	private static final Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);
	
	@Autowired
	@Qualifier("AccountValidator")
	private ServiceValidator validateAccount;
	
	@Autowired
	@Qualifier("TransferValidator")
	private ServiceValidator validateTransfer;
	
	@Autowired
	@Qualifier("CustomerAccountHandler")
	private AccountHandler handleAccount;
	
	@Autowired
	@Qualifier("MoneyTransferHandler")
	private TransferHandler handleTransfer;

	@Override
	public AccountResponse createAccount(AccountRequest request) {
		
		validateAccount.validate(request);
		
		logger.debug("Processing account creation request");
		
		return handleAccount.createAccount(request);
	}

	@Override
	public TransferResponse makeTransfer(TransferRequest request) {
		
		validateTransfer.validate(request);
		
		logger.debug("Processing transfer request");
		
		return handleTransfer.makeTransfer(request);
	}

}
