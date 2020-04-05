package com.cg.DAO;

import java.util.List;

import com.cg.DTO.Property;

public interface DaoSeller {

	List<Property> addProperty(Property property);
	List<Property> deleteProperty(String propId);
}
