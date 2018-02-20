package com.payment.transfer.mapper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.payment.transfer.TransferServiceUtil;
import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;
import com.payment.transfer.model.Transfer;

@Component("MoneyTransferMapper")
public class MoneyTransferMapper implements TransferMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(MoneyTransferMapper.class);

	@Override
	public Transfer convertTransferRequestToTransfer(TransferRequest request) {
		
		logger.debug("mapping transfer request to transfer");
		
		Transfer transfer = new Transfer();
		transfer.setAmount(request.getAmount());
		transfer.setReceiverAccountName(request.getReceiverAccountName());
		transfer.setSenderAccountName(request.getSenderAccountName());
		transfer.setTransferId(TransferServiceUtil.generateTransferID());
		transfer.setTransferDate(new Date());
		
		return transfer;
	}

	@Override
	public TransferResponse convertTransferToTransferResponse(Transfer transfer) {
		
		logger.debug("mapping transfer to transfer response");
		
		TransferResponse response = new TransferResponse();
		response.setTransferId(transfer.getTransferId());
		response.setTransferDate(transfer.getFormattedTransferDate());
		
		return response;
	}

}
