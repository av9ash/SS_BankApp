package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_event_log")
public class TblEventLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "event_id", unique = true, nullable = false)
	private int eventId;
	@Column(name = "event_name")
	private String eventName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	private Date createdDate;
	@Column(name = "event_type")
	private Integer eventType;

	public TblEventLog() {
	}

	public TblEventLog(int eventId) {
		this.eventId = eventId;
	}

	public TblEventLog(int eventId, String eventName, Date createdDate, Integer eventType) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.createdDate = createdDate;
		this.eventType = eventType;
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
}
