package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_account")
public class TblAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "account_id", unique = true, nullable = false)
	private int accountId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private TblUser tblUser;
	@Column(name = "account_type")
	private Integer accountType;
	@Column(name = "current_amount")
	private Long currentAmount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	private Date createdDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblAccountByFromAccount")
	private Set<TblTransaction> tblTransactionsForFromAccount = new HashSet(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblAccountByToAccount")
	private Set<TblTransaction> tblTransactionsForToAccount = new HashSet(0);

	public TblAccount() {
	}

	public TblAccount(int accountId) {
		this.accountId = accountId;
	}

	public TblAccount(int accountId, TblUser tblUser, Integer accountType, Long currentAmount, Date createdDate,
			Set<TblTransaction> tblTransactionsForFromAccount, Set<TblTransaction> tblTransactionsForToAccount) {
		this.accountId = accountId;
		this.tblUser = tblUser;
		this.accountType = accountType;
		this.currentAmount = currentAmount;
		this.createdDate = createdDate;
		this.tblTransactionsForFromAccount = tblTransactionsForFromAccount;
		this.tblTransactionsForToAccount = tblTransactionsForToAccount;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Long getCurrentAmount() {
		return this.currentAmount;
	}

	public void setCurrentAmount(Long currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<TblTransaction> getTblTransactionsForFromAccount() {
		return this.tblTransactionsForFromAccount;
	}

	public void setTblTransactionsForFromAccount(Set<TblTransaction> tblTransactionsForFromAccount) {
		this.tblTransactionsForFromAccount = tblTransactionsForFromAccount;
	}

	public Set<TblTransaction> getTblTransactionsForToAccount() {
		return this.tblTransactionsForToAccount;
	}

	public void setTblTransactionsForToAccount(Set<TblTransaction> tblTransactionsForToAccount) {
		this.tblTransactionsForToAccount = tblTransactionsForToAccount;
	}
}
