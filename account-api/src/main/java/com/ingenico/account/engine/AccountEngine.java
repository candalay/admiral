package com.ingenico.account.engine;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.security.InvalidParameterException;
import java.util.UUID;

import javax.inject.Inject;

import org.h2.util.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingenico.account.model.Account;
import com.ingenico.account.repository.AccountRepository;
import com.ingenico.exception.AccountAlreadyExistException;

@Component
@JsonInclude(NON_NULL)
public class AccountEngine {

	private UUID id;

	@Inject
	AccountRepository accountRepository;

	@JsonProperty
	public UUID id() {
		return id;
	}

	public AccountEngine createAccount(String accountNumber, Double balance) {

		this.id = UUID.randomUUID();
		
		if(StringUtils.isNullOrEmpty(accountNumber)) {
			throw new InvalidParameterException("`Account number is missing");
		}
		
		try {
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(balance);
			account.setAccountInstanceId(this.id);
			accountRepository.save(account);
			AccountEngine result = new AccountEngine();
			result.setId(id);

			return result;
		} catch (DataIntegrityViolationException e) {
			throw new AccountAlreadyExistException("Account is already exist`");
		} 
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
