package com.cg.DTO;

import java.io.Serializable;

public class Address implements Serializable {
	
	private String houseNo;
	
	private String city;
	
	private String state;
	
	private String locality;
	public Address(String houseNo, String city, String state, String locality) {
		super();
		this.houseNo = houseNo;
		this.city = city;
		this.state = state;
		this.locality = locality;
	}
	public Address(String city, String state) {
		super();
		this.city = city;
		this.state = state;

	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address houseNo=" + houseNo + ", city=" + city + ", state=" + state + ", locality=" + locality + "]";
	}
	
	public String toStringCustom() {
		return   houseNo + " "+ city + " " + state + " " + locality;
	}
	public String getCity(){
		return this.city;
	}
}
