package com.payment.transfer.service;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;

public interface TransferService {

	public AccountResponse createAccount(AccountRequest request);

	public TransferResponse makeTransfer(TransferRequest request);

}
