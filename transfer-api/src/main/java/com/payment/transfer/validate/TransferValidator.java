package com.payment.transfer.validate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.exception.TransferServiceValidationException;

@Component("TransferValidator")
public class TransferValidator implements ServiceValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(TransferValidator.class);

	@Override
	public void validate(Object object) {
		
		logger.debug("Validating transfer request");
		
		TransferRequest transferRequest = (TransferRequest) object	;
		
		if(StringUtils.isBlank(transferRequest.getReceiverAccountName())) {
			throw new TransferServiceValidationException("Receiver account name can not be empty!");
		}
		
		if(StringUtils.isBlank(transferRequest.getSenderAccountName())) {
			throw new TransferServiceValidationException("Sender account name can not be empty!");
		}
		
		if(transferRequest.getAmount() <=0 ) {
			throw new TransferServiceValidationException("Account balance can not be equal or less than zero!");
		}
		
	}

}
