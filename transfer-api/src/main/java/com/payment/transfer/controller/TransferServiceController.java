package com.payment.transfer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.payment.transfer.TransferServiceConstants;
import com.payment.transfer.TransferServiceUtil;
import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;
import com.payment.transfer.service.TransferService;

@RestController
@RequestMapping("/transfer/v1/api/")
public class TransferServiceController {

	private static final Logger logger = LoggerFactory.getLogger(TransferServiceController.class);

	@Autowired
	@Qualifier("MoneyTransferService")
	TransferService transferService;

	@Async
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public CompletableFuture<Map<String, Object>> createAccount(@RequestBody AccountRequest request) {

		TransferServiceUtil.randomSleep(3000, TimeUnit.MILLISECONDS);
		
		logger.debug("creating new account {}", Thread.currentThread().getName());
		
		AccountResponse response = transferService.createAccount(request);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(TransferServiceConstants.RESULT, TransferServiceConstants.SUCCESS);
		result.put(TransferServiceConstants.ACCOUNT, response);

		return CompletableFuture.completedFuture(result);	
	}

	@Async
	@RequestMapping(value = "/makeTransfer", method = RequestMethod.POST)
	public CompletableFuture<Map<String, Object>> makeTransfer(@RequestBody TransferRequest request) {
		
		TransferServiceUtil.randomSleep(3000, TimeUnit.MILLISECONDS);

		logger.debug("processing transfer request {}", Thread.currentThread().getName());

		TransferResponse response = transferService.makeTransfer(request);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(TransferServiceConstants.RESULT, TransferServiceConstants.SUCCESS);
		result.put(TransferServiceConstants.TRANSFER, response);

		return CompletableFuture.completedFuture(result);	
	}

}
