package com.payment.transfer.handler;

import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;

public interface TransferHandler {

	TransferResponse makeTransfer(TransferRequest request);
}
