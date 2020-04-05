package com.cg.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.DTO.Filter;

public class Validation {

	public boolean isNameValid(String name){
		String regex = "^[A-Za-z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if(matcher != null)
			return true;
		else
			return false;
		
	}

	public boolean isEmailValid(String email){
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if(matcher != null)
				return true;
			else
				return false;
	}

	public boolean isPropPriceValid(String price){
		
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(price);
		if(matcher != null)
			return true;
		else
			return false;
		

	}

	public boolean isPropIdValid(String id){
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		if(matcher != null)
			return true;
		else
			return false;

	}

	public boolean isPasswordValid(String password){
		String regex = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if(matcher != null)
			return true;
		else
			return false;

	}
	
	public Filter isFilterValid(Filter filter){
		if(!isPropPriceValid(filter.getMaxPrice()))
			filter.setMaxPrice("9999999999999");
		if(!isPropPriceValid(filter.getMinPrice()))
			filter.setMinPrice("0");
		if(!isNameValid(filter.getLandmark()))
			filter.setLandmark(null);
		if(!isNameValid(filter.getLocation()))
			filter.setLocation(null);
		return filter;
	}
	
	public boolean isUserTypeVaild(String userType){
		if( userType.equalsIgnoreCase("Buyer") || userType.equalsIgnoreCase("Seller"))
			return true;
		else
			return false;
	}
}
