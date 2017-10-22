package com.ingenico.account.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ingenico.account.model.Transfer;

@Repository
public interface TransferRepository extends BaseRepository<Transfer, Long> {

	Transfer findTransferByTransactionId(UUID id);


}
