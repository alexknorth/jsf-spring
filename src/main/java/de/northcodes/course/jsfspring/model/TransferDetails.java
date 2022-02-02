package de.northcodes.course.jsfspring.model;

import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = AbstractEntity.BANK_PREFIX + "transfer")
public class TransferDetails extends AbstractEntity{

	@Column(name = "transfer_date", nullable = false)
	private Date transferDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="fk_transferredBy",referencedColumnName="id",nullable=false,unique=false)
	private User transferredBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="fk_transferTo",referencedColumnName="id",nullable=false,unique=false)
	private User transferredTo;

	@Enumerated(EnumType.STRING)
	@Column(name = "transfer_state", nullable = false)
	private TransferState transferState;

	@Column(name = "transfer_amount", nullable = false)
	private double amount;
    
	public TransferDetails() {
	}

	public TransferDetails(Date transferDate, User transferredBy, User transferredTo, TransferState transferState, double amount) {
		this.transferDate = transferDate;
		this.transferredBy = transferredBy;
		this.transferredTo = transferredTo;
		this.transferState = transferState;
		this.amount = amount;
	}


	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public User getTransferredBy() {
		return transferredBy;
	}

	public void setTransferredBy(User transferredBy) {
		this.transferredBy = transferredBy;
	}

	public User getTransferredTo() {
		return transferredTo;
	}

	public void setTransferredTo(User transferredTo) {
		this.transferredTo = transferredTo;
	}

	public TransferState getTransferState() {
		return transferState;
	}

	public void setTransferState(TransferState transferState) {
		this.transferState = transferState;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
