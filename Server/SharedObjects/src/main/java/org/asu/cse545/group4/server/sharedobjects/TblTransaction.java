package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_transaction")
public class TblTransaction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "transaction_id", unique = true, nullable = false)
	private int transactionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_account")
	private TblAccount tblAccountByFromAccount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_account")
	private TblAccount tblAccountByToAccount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_approved_by")
	private TblUser tblUser;
	@Column(name = "transaction_type")
	private Integer transactionType;
	@Column(name = "transaction_status")
	private Integer transactionStatus;
	@Column(name = "transaction_amount")
	private Integer transactionAmount;
	@Column(name = "is_critical_transaction")
	private Integer isCriticalTransaction;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_created_date", length = 19)
	private Date transactionCreatedDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_updated_date", length = 19)
	private Date transactionUpdatedDate;

	public TblTransaction() {
	}

	public TblTransaction(int transactionId) {
		this.transactionId = transactionId;
	}

	public TblTransaction(int transactionId, TblAccount tblAccountByFromAccount, TblAccount tblAccountByToAccount,
			TblUser tblUser, Integer transactionType, Integer transactionStatus, Integer transactionAmount,
			Integer isCriticalTransaction, Date transactionCreatedDate, Date transactionUpdatedDate) {
		this.transactionId = transactionId;
		this.tblAccountByFromAccount = tblAccountByFromAccount;
		this.tblAccountByToAccount = tblAccountByToAccount;
		this.tblUser = tblUser;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.transactionAmount = transactionAmount;
		this.isCriticalTransaction = isCriticalTransaction;
		this.transactionCreatedDate = transactionCreatedDate;
		this.transactionUpdatedDate = transactionUpdatedDate;
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TblAccount getTblAccountByFromAccount() {
		return this.tblAccountByFromAccount;
	}

	public void setTblAccountByFromAccount(TblAccount tblAccountByFromAccount) {
		this.tblAccountByFromAccount = tblAccountByFromAccount;
	}

	public TblAccount getTblAccountByToAccount() {
		return this.tblAccountByToAccount;
	}

	public void setTblAccountByToAccount(TblAccount tblAccountByToAccount) {
		this.tblAccountByToAccount = tblAccountByToAccount;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public Integer getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Integer getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Integer getIsCriticalTransaction() {
		return this.isCriticalTransaction;
	}

	public void setIsCriticalTransaction(Integer isCriticalTransaction) {
		this.isCriticalTransaction = isCriticalTransaction;
	}

	public Date getTransactionCreatedDate() {
		return this.transactionCreatedDate;
	}

	public void setTransactionCreatedDate(Date transactionCreatedDate) {
		this.transactionCreatedDate = transactionCreatedDate;
	}

	public Date getTransactionUpdatedDate() {
		return this.transactionUpdatedDate;
	}

	public void setTransactionUpdatedDate(Date transactionUpdatedDate) {
		this.transactionUpdatedDate = transactionUpdatedDate;
	}
}
