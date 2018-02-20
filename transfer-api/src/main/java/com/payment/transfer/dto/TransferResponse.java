package com.payment.transfer.dto;

import java.util.UUID;

public class TransferResponse {

	private UUID transferId;
	private String transferDate;
	
	public UUID getTransferId() {
		return transferId;
	}
	public void setTransferId(UUID transferId) {
		this.transferId = transferId;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
}
