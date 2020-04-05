package com.cg.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="property")
public class Property implements Serializable{
	@Column(name="prop_address")
	private Address propAddress;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	//@SequenceGenerator(name="myseq",sequenceName="prop_id") 
	@Column(name="prop_id")
	private  int propId;
	//private static int propCounter = 0;
	@Column(name="prop_price")
	private String propPrice;
	@Column(name="prop_landmark")
	private String landmark;
	@Column(name="prop_description")
	private String propDescription;
	@Enumerated(EnumType.STRING)
	private PropertyType type;
	@OneToOne
	@JoinColumn(name="user", nullable=false)
	private User owner;

	public Property() {
		super();
	}
	public Property(Address propAddress, String propPrice, String landmark, String propDescription,
			PropertyType type) {
		super();
		this.propAddress = propAddress;
		//this.propId = Integer.toString(propCounter++);
		this.propPrice = propPrice;
		this.landmark = landmark;
		this.propDescription = propDescription;
		this.type = type;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String viewProp() {
		return "Property [propAddress=" + propAddress + ", propId=" + propId + ", propPrice=" + propPrice
				+ ", landmark=" + landmark + ", propDescription=" + propDescription + ", type=" + type + "]+\n";
	}
	public String getPropId() {
		return propId+"";
	}
	public void setPropId(String propId) {
		this.propId = Integer.parseInt(propId);
	}
	public String getPropPrice() {
		return propPrice;
	}
	public String getLandmark() {
		return landmark;
	}
	public Address getPropAddress() {
		return propAddress;
	}
	public void setPropAddress(Address propAddress) {
		this.propAddress = propAddress;
	}

	public String getPropDescription() {
		return propDescription;
	}
	public void setPropDescription(String propDescription) {
		this.propDescription = propDescription;
	}
	public void setPropPrice(String propPrice) {
		this.propPrice = propPrice;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public void setType(PropertyType type) {
		this.type = type;
	}
	public PropertyType getType() {
		return type;
	}
	public String getCity(){
		return propAddress.getCity();
	}
	@Override
	public String toString() {
		return " "+propId+" "+ getType()+" "+getLandmark()+"\n";
	}
}
