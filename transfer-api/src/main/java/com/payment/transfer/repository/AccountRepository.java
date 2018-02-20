package com.payment.transfer.repository;

import org.springframework.stereotype.Repository;

import com.payment.transfer.model.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {

	Account findAccountByAccountName(String accountName);
}
