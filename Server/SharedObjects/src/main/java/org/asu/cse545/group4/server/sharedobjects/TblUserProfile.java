package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_user_profile")
public class TblUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "org.asu.cse545.group4.server.sharedobjects.TblUserProfileIdGenerator", strategy = "foreign", parameters = {
			@org.hibernate.annotations.Parameter(name = "property", value = "tblUser") })
	@Id
	@GeneratedValue(generator = "org.asu.cse545.group4.server.sharedobjects.TblUserProfileIdGenerator")
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private TblUser tblUser;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "tier")
	private Integer tier;
	@Column(name = "address1")
	private String address1;
	@Column(name = "address2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "province")
	private String province;
	@Column(name = "zip")
	private Integer zip;

	public TblUserProfile() {
	}

	public TblUserProfile(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public TblUserProfile(TblUser tblUser, String firstName, String middleName, String lastName, String email,
			String phone, Integer tier, String address1, String address2, String city, String province, Integer zip) {
		this.tblUser = tblUser;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.tier = tier;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.zip = zip;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getTier() {
		return this.tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	@Column(name = "address1")
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getZip() {
		return this.zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}
}
