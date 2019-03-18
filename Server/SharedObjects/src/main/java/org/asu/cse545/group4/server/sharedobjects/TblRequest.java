package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_request")
public class TblRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "request_id", unique = true, nullable = false)
	private int requestId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "request_assigned_to")
	private TblUser tblUserByRequestAssignedTo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requested_by")
	private TblUser tblUserByRequestedBy;
	@Column(name = "type_of_request")
	private Integer typeOfRequest;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_id")
	private TblTransaction tblTransaction;

	public TblRequest() {
	}

	public TblRequest(int requestId) {
		this.requestId = requestId;
	}

	public TblRequest(int requestId, TblUser tblUserByRequestAssignedTo, TblUser tblUserByRequestedBy,
			Integer typeOfRequest, TblTransaction tblTransaction) {
		this.requestId = requestId;
		this.tblUserByRequestAssignedTo = tblUserByRequestAssignedTo;
		this.tblUserByRequestedBy = tblUserByRequestedBy;
		this.typeOfRequest = typeOfRequest;
		this.tblTransaction = tblTransaction;
	}

	public int getRequestId() {
		return this.requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public TblUser getTblUserByRequestAssignedTo() {
		return this.tblUserByRequestAssignedTo;
	}

	public void setTblUserByRequestAssignedTo(TblUser tblUserByRequestAssignedTo) {
		this.tblUserByRequestAssignedTo = tblUserByRequestAssignedTo;
	}

	public TblUser getTblUserByRequestedBy() {
		return this.tblUserByRequestedBy;
	}

	public void setTblUserByRequestedBy(TblUser tblUserByRequestedBy) {
		this.tblUserByRequestedBy = tblUserByRequestedBy;
	}

	public Integer getTypeOfRequest() {
		return this.typeOfRequest;
	}

	public void setTypeOfRequest(Integer typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}
	
	public TblTransaction getTblTransaction() {
		return this.tblTransaction;
	}

	public void setTblTransaction(TblTransaction tblTransaction) {
		this.tblTransaction = tblTransaction;
	}
}
