package com.ingenico.account.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ingenico.account.model.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>{
	
	Account findAccountByAccountNumber(String accountNumber);
	
	Account findAccountByAccountInstanceId(UUID id);

}
