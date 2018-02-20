package com.payment.transfer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;
import com.payment.transfer.exception.TransferServiceBusinessException;
import com.payment.transfer.mapper.TransferMapper;
import com.payment.transfer.model.Account;
import com.payment.transfer.model.Transfer;
import com.payment.transfer.repository.AccountRepository;
import com.payment.transfer.repository.TransferRepository;

@Component("MoneyTransferHandler")
public class MoneyTransferHandler implements TransferHandler 	{
	
	private static final Logger logger = LoggerFactory.getLogger(MoneyTransferHandler.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransferRepository transferRepository;
	
	@Autowired
	@Qualifier("MoneyTransferMapper")
	TransferMapper transferMapper;

	@Override
	public TransferResponse makeTransfer(TransferRequest request) {
		
		logger.debug("processing money transfer ");
		
		Account sender = accountRepository.findAccountByAccountName(request.getSenderAccountName());
		Account receiver = accountRepository.findAccountByAccountName(request.getReceiverAccountName());
		
		checkSenderAndReceiverEligibilty(sender, receiver, request);
		
		Transfer transfer = transferMapper.convertTransferRequestToTransfer(request);
		
		updateAccountInfoAfterTransfer(request, sender, receiver);
		
		transferRepository.save(transfer);
		
		return transferMapper.convertTransferToTransferResponse(transfer);
	}

	private void updateAccountInfoAfterTransfer(TransferRequest request, Account sender, Account receiver) {
		
		double senderBalance = sender.getBalance();
		double receiverBalance = receiver.getBalance();
		double transferAmount = request.getAmount();
		
		sender.setBalance(senderBalance - transferAmount);
		receiver.setBalance(receiverBalance + transferAmount);
		
		accountRepository.save(sender);
		accountRepository.save(receiver);
	}

	private void checkSenderAndReceiverEligibilty(Account sender, Account receiver, TransferRequest request) {
				
		if(null == sender) {
			throw new TransferServiceBusinessException("Sender does not exist!");
		}
		
		if(null == receiver) {
			throw new TransferServiceBusinessException("Receiver does not exist!");
		}
		
		if(sender.getBalance() < request.getAmount()) {
			throw new TransferServiceBusinessException("Amount is higher than your balance!");
		}
		
	}

}
