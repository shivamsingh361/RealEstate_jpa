package com.cg.Presentation;

import com.cg.DTO.PropertyType;
import com.cg.DTO.UserType;
import com.cg.exception.UserException;

public class Decoder {
	public PropertyType decodeType(String input) {
		int key = 0;
		try {
			key = Integer.parseInt(input);
		} catch (Exception e) {
			//System.out.println("e");
		}
		switch (key) {
		case 1:
			return PropertyType.APPARTMENT;
		case 2:
			return PropertyType.FLAT;
		case 3:
			return PropertyType.HOUSE;
		case 4:
			return PropertyType.VILLA;
		default:
			return PropertyType.HOUSE;
		}
	}
	
	public UserType decodeUser(String input) {
		switch (input) {
		case "1":
			return UserType.BUYER;
		case "2":
			return UserType.SELLER;
		default:
			return UserType.BUYER;
		}
	}

}
