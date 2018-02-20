package com.payment.transfer.handler;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;

public interface AccountHandler {

	AccountResponse createAccount(AccountRequest request);

}
