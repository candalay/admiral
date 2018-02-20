package com.payment.transfer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.exception.TransferServiceBusinessException;
import com.payment.transfer.mapper.AccountMapper;
import com.payment.transfer.model.Account;
import com.payment.transfer.repository.AccountRepository;

@Component("CustomerAccountHandler")
public class CustomerAccountHandler implements AccountHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerAccountHandler.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	@Qualifier("CustomerAccountMapper")
	AccountMapper accountMapper;

	@Override
	public AccountResponse createAccount(AccountRequest request) {
		
		logger.debug("creating account");
		
		Account account = accountRepository.findAccountByAccountName(request.getName());
		
		if(null != account) {
			throw new TransferServiceBusinessException("Account is already exist!");
		}
		
		Account newAccount = accountMapper.convertAccountReqToAccount(request);
		
		accountRepository.save(newAccount);

		return accountMapper.convertAccountToAccountResponse(newAccount);
	}

}
