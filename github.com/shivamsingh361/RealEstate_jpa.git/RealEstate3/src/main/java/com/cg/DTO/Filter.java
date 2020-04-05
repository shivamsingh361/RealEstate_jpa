package com.cg.DTO;

public class Filter {

	private String location;
	private String maxPrice;
	private String minPrice;
	private PropertyType type;
	private String landmark;
	public Filter(String location, String maxPrice, String minPrice, PropertyType type, String landmark) {
		super();
		this.location = location;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.type = type;		//Villa, House, Flat
		this.landmark = landmark;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public PropertyType getType() {
		return type;
	}
	public void setType(PropertyType type) {
		this.type = type;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	
}
