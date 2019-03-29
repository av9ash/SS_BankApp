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
@Table(name = "tbl_appointment")
public class TblAppointment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "appointment_id", unique = true, nullable = false)
	private int appointmentId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assigned_to_user_id")
	private TblUser tblUserByAssignedToUserId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appointment_user_id")
	private TblUser tblUserByAppointmentUserId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	private Date createdDate;
	@Column(name = "appointment_status")
	private Integer appointmentStatus;
	@Column(name = "descript")
	private String descript;

	public TblAppointment() {
	}

	public TblAppointment(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public TblAppointment(int appointmentId, TblUser tblUserByAssignedToUserId, TblUser tblUserByAppointmentUserId,
			Date createdDate, Integer appointmentStatus, String dscript) {
		this.appointmentId = appointmentId;
		this.tblUserByAssignedToUserId = tblUserByAssignedToUserId;
		this.tblUserByAppointmentUserId = tblUserByAppointmentUserId;
		this.createdDate = createdDate;
		this.appointmentStatus = appointmentStatus;
		this.descript = dscript;
	}

	public int getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public TblUser getTblUserByAssignedToUserId() {
		return this.tblUserByAssignedToUserId;
	}

	public void setTblUserByAssignedToUserId(TblUser tblUserByAssignedToUserId) {
		this.tblUserByAssignedToUserId = tblUserByAssignedToUserId;
	}

	public TblUser getTblUserByAppointmentUserId() {
		return this.tblUserByAppointmentUserId;
	}

	public void setTblUserByAppointmentUserId(TblUser tblUserByAppointmentUserId) {
		this.tblUserByAppointmentUserId = tblUserByAppointmentUserId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(Integer appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
