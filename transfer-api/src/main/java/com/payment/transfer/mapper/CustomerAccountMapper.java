package com.payment.transfer.mapper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.model.Account;

@Component("CustomerAccountMapper")
public class CustomerAccountMapper implements AccountMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerAccountMapper.class);

	@Override
	public Account convertAccountReqToAccount(AccountRequest request) {
		
		logger.debug("mapping account request to account");
		
		Account account = new Account();
		account.setAccountName(request.getName());
		account.setBalance(request.getBalance());
		account.setCreatedDate(new Date());
		
		return account;
	}

	@Override
	public AccountResponse convertAccountToAccountResponse(Account account) {
		
		logger.debug("mapping account to account response");
		
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setName(account.getAccountName());
		accountResponse.setBalance(account.getBalance());
		accountResponse.setAccountCreationDate(account.getFormattedAccountCreationDate());
		
		return accountResponse;
	}

}
