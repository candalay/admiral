package com.payment.transfer.validate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.exception.TransferServiceValidationException;

@Component("AccountValidator")
public class AccountValidator implements ServiceValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountValidator.class);

	@Override
	public void validate(Object object) {
		
		logger.debug("Validating account request");
		
		AccountRequest accountRequest = (AccountRequest) object	;
		
		if(StringUtils.isBlank(accountRequest.getName())) {
			throw new TransferServiceValidationException("Account name can not be empty!");
		}
		
		if(0 > accountRequest.getBalance()) {
			throw new TransferServiceValidationException("Account balance can not be less than zero!");
		}
		
	}

}
