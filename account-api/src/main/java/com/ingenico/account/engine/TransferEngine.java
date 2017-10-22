package com.ingenico.account.engine;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.security.InvalidParameterException;
import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingenico.account.model.Account;
import com.ingenico.account.model.Transfer;
import com.ingenico.account.repository.AccountRepository;
import com.ingenico.account.repository.TransferRepository;
import com.ingenico.exception.IllegalOperationException;

@Component
@JsonInclude(NON_NULL)
public class TransferEngine {
	
	Transfer transfer;
	
	private UUID id;

	@Inject
	AccountRepository accountRepository;
	
	@Inject
	TransferRepository transferRepository;

	@JsonProperty
	public UUID id() {
		return id;
	}

	@Transactional
	public TransferEngine checkTransferRequest(UUID uuid, Transfer transfer) {
	
		Account receiver = accountRepository.findAccountByAccountNumber(transfer.getReceiverAccountNumber());		
		Account sender = accountRepository.findAccountByAccountNumber(transfer.getSenderAccountNumber());
		
		if(null == receiver) {
			throw new InvalidParameterException("Check receiver account information ");
		}
		
		if(null == sender) {
			throw new InvalidParameterException("Check sender account information ");
		}
		
		if(sender.getBalance() < transfer.getAmount()) {
			throw new IllegalOperationException("Insufficient balance!");
		}
	

		TransferEngine checkedResult = new TransferEngine();
		checkedResult.setTransfer(transfer);
		checkedResult.setId(uuid);
	

		return checkedResult;
	}

	@Transactional
	public TransferEngine completeTransfer(TransferEngine transferEngine) {
		
	;
		Transfer transfer = transferEngine.getTransfer();
		transfer.setTransactionId(transferEngine.getId());
		transferRepository.save(transfer);
		
		Account receiver = accountRepository.findAccountByAccountNumber(transfer.getReceiverAccountNumber());
		Double updateReceiverBalance = receiver.getBalance() + transfer.getAmount();
		receiver.setBalance(updateReceiverBalance);
		
		Account sender = accountRepository.findAccountByAccountNumber(transfer.getSenderAccountNumber());
		Double updateSenderBalance = sender.getBalance() - transfer.getAmount();
		sender.setBalance(updateSenderBalance);
		
		return transferEngine;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
