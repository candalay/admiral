package com.payment.transfer.mapper;

import com.payment.transfer.dto.TransferRequest;
import com.payment.transfer.dto.TransferResponse;
import com.payment.transfer.model.Transfer;


public interface TransferMapper {

	Transfer convertTransferRequestToTransfer(TransferRequest request);

	TransferResponse convertTransferToTransferResponse(Transfer transfer);

}
