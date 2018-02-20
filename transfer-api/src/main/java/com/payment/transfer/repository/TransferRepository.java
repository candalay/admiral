package com.payment.transfer.repository;

import org.springframework.stereotype.Repository;

import com.payment.transfer.model.Transfer;

@Repository
public interface TransferRepository extends BaseRepository<Transfer, Long> {

}
