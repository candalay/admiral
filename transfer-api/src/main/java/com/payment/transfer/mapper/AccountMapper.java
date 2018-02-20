package com.payment.transfer.mapper;

import com.payment.transfer.dto.AccountRequest;
import com.payment.transfer.dto.AccountResponse;
import com.payment.transfer.model.Account;

public interface AccountMapper {

	Account convertAccountReqToAccount(AccountRequest request);

	AccountResponse convertAccountToAccountResponse(Account account);

}
