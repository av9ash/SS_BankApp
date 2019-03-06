package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_user")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private Integer status;
	@Column(name = "incorrect_attempts")
	private Integer incorrectAttempts;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", length = 19)
	private Date modifiedDate;
	@Column(name = "is_external_user")
	private Integer isExternalUser;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUserByAssignedToUserId")
	private Set<TblAppointment> tblAppointmentsForAssignedToUserId = new HashSet(0);
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "tblUser")
	private TblUserProfile tblUserProfile;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUserByAppointmentUserId")
	private Set<TblAppointment> tblAppointmentsForAppointmentUserId = new HashSet(0);
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUser")
	private Set<TblAccount> tblAccounts = new HashSet(0);
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUser")
	private Set<TblTransaction> tblTransactions = new HashSet(0);
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUserByRequestAssignedTo")
	private Set<TblRequest> tblRequestsForRequestAssignedTo = new HashSet(0);
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblUserByRequestedBy")
	private Set<TblRequest> tblRequestsForRequestedBy = new HashSet(0);

	public TblUser() {
	}

	public TblUser(int userId) {
		this.userId = userId;
	}

	public TblUser(int userId, String username, String password, Integer status, Integer incorrectAttempts,
			Date createdDate, Date modifiedDate, Integer isExternalUser,
			Set<TblAppointment> tblAppointmentsForAssignedToUserId, TblUserProfile tblUserProfile,
			Set<TblAppointment> tblAppointmentsForAppointmentUserId, Set<TblAccount> tblAccounts,
			Set<TblTransaction> tblTransactions, Set<TblRequest> tblRequestsForRequestAssignedTo,
			Set<TblRequest> tblRequestsForRequestedBy) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.status = status;
		this.incorrectAttempts = incorrectAttempts;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.isExternalUser = isExternalUser;
		this.tblAppointmentsForAssignedToUserId = tblAppointmentsForAssignedToUserId;
		this.tblUserProfile = tblUserProfile;
		this.tblAppointmentsForAppointmentUserId = tblAppointmentsForAppointmentUserId;
		this.tblAccounts = tblAccounts;
		this.tblTransactions = tblTransactions;
		this.tblRequestsForRequestAssignedTo = tblRequestsForRequestAssignedTo;
		this.tblRequestsForRequestedBy = tblRequestsForRequestedBy;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIncorrectAttempts() {
		return this.incorrectAttempts;
	}

	public void setIncorrectAttempts(Integer incorrectAttempts) {
		this.incorrectAttempts = incorrectAttempts;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getIsExternalUser() {
		return this.isExternalUser;
	}

	public void setIsExternalUser(Integer isExternalUser) {
		this.isExternalUser = isExternalUser;
	}

	public Set<TblAppointment> getTblAppointmentsForAssignedToUserId() {
		return this.tblAppointmentsForAssignedToUserId;
	}

	public void setTblAppointmentsForAssignedToUserId(Set<TblAppointment> tblAppointmentsForAssignedToUserId) {
		this.tblAppointmentsForAssignedToUserId = tblAppointmentsForAssignedToUserId;
	}

	public TblUserProfile getTblUserProfile() {
		return this.tblUserProfile;
	}

	public void setTblUserProfile(TblUserProfile tblUserProfile) {
		this.tblUserProfile = tblUserProfile;
	}

	public Set<TblAppointment> getTblAppointmentsForAppointmentUserId() {
		return this.tblAppointmentsForAppointmentUserId;
	}

	public void setTblAppointmentsForAppointmentUserId(Set<TblAppointment> tblAppointmentsForAppointmentUserId) {
		this.tblAppointmentsForAppointmentUserId = tblAppointmentsForAppointmentUserId;
	}

	public Set<TblAccount> getTblAccounts() {
		return this.tblAccounts;
	}

	public void setTblAccounts(Set<TblAccount> tblAccounts) {
		this.tblAccounts = tblAccounts;
	}

	public Set<TblTransaction> getTblTransactions() {
		return this.tblTransactions;
	}

	public void setTblTransactions(Set<TblTransaction> tblTransactions) {
		this.tblTransactions = tblTransactions;
	}

	public Set<TblRequest> getTblRequestsForRequestAssignedTo() {
		return this.tblRequestsForRequestAssignedTo;
	}

	public void setTblRequestsForRequestAssignedTo(Set<TblRequest> tblRequestsForRequestAssignedTo) {
		this.tblRequestsForRequestAssignedTo = tblRequestsForRequestAssignedTo;
	}

	public Set<TblRequest> getTblRequestsForRequestedBy() {
		return this.tblRequestsForRequestedBy;
	}

	public void setTblRequestsForRequestedBy(Set<TblRequest> tblRequestsForRequestedBy) {
		this.tblRequestsForRequestedBy = tblRequestsForRequestedBy;
	}
}
