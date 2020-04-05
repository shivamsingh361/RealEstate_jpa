package com.cg.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	@Enumerated(EnumType.STRING)
	public UserType type = UserType.BUYER;
	@Id
	@Column(name="login_id")
	private String loginId;
	@Column(name="password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name="phone_no")
	private String phoneNo;
	@Column(name="address")
	private Address address;	
	
	public User() {
		super();
	}

	public User(UserType type, String loginId, String password, String name, String phoneNo, Address address) {
		
		super();
		this.type = type;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [type=" + type + ", loginId=" + loginId + ", password=" + password + ", name=" + name
				+ ", phoneNo=" + phoneNo + ", address=" + address + "]";
	}
}
