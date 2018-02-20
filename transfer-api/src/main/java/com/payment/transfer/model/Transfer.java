package com.payment.transfer.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.payment.transfer.TransferServiceUtil;

@Entity
@Table(name = "t_transfer")
public class Transfer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transfer_id")
	private UUID transferId;

	@Column(name = "sender_acc_name")
	private String senderAccountName;

	@Column(name = "receiver_acc_name")
	private String receiverAccountName;

	@Column(name = "amount")
	private double amount;

	@Column(name = "transfer_date")
	private Date transferDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getTransferId() {
		return transferId;
	}

	public void setTransferId(UUID transferId) {
		this.transferId = transferId;
	}

	public String getSenderAccountName() {
		return senderAccountName;
	}

	public void setSenderAccountName(String senderAccountName) {
		this.senderAccountName = senderAccountName;
	}

	public String getReceiverAccountName() {
		return receiverAccountName;
	}

	public void setReceiverAccountName(String receiverAccountName) {
		this.receiverAccountName = receiverAccountName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getFormattedTransferDate() {
		return TransferServiceUtil.getDateAsTimeStamp(this.transferDate).toString();
	}

}
