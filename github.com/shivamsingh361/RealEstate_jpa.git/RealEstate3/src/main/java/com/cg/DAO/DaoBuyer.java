package com.cg.DAO;

import java.util.List;

import com.cg.DTO.Filter;
import com.cg.DTO.Property;
import com.cg.DTO.User;

public interface DaoBuyer {

	List<Property> searchProperty(Filter filter);	// returns list of property object
	User updateUser(String id, User user);
	boolean updatePassword(String id, String newPassword);
	Property getPropertyById(String propId);
}
