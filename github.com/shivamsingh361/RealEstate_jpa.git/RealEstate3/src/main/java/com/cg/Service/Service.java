package com.cg.Service;

import java.util.List;

import com.cg.DTO.*;

public interface Service {
	abstract int verifyOTP(String id);			// Return 6-digit OTP and sends same to registered userid also veryfy the user id if exist.
	abstract String updatePassword(String oldPass, String newPass); //update password of user.
	abstract boolean forgotPassword(String id, String newPass);
	//
	abstract List<Property> userHome();
	//abstract String Home();
	//
	abstract boolean login(String id, String pass);
	abstract String Register(User user);
	//Buyer
	abstract List<Property> Search(Filter filter);
	abstract User updateUserProfile(String Name, String contact );
	//
	abstract String logout();
	
	//Seller
	List<Property> addProperty(Property prop);
	List<Property> deleteProperty(String propId);
	abstract User getUser();
	abstract Property viewProperty(String propId);
	
}