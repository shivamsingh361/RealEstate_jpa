package com.cg.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.cg.DTO.*;
//import com.cg.DAO.*;



public class DaoBuyerImpl implements DaoBuyer {
	private EntityManagerFactory emf;
	private EntityManager em;
	public List<Property> searchProperty(Filter filter) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Property> propLst = em.createQuery("from Property",Property.class).getResultList(); 
		List<Property> propList = new ArrayList<Property>();

		/*
		 * Map<String, Property> temp = StaticDB.getPropertyMap(); Set<Entry<String,
		 * Property>> prop = temp.entrySet(); if(filter == null) { for(Entry<String,
		 * Property> tem : prop){ Property property = (Property)tem.getValue();
		 * propList.add(property); }
		 */
		System.out.println(propLst);
		if(filter == null){
			return propLst;
		}
		else
			for(Property property : propLst){
				if(!property.getCity().equals("-")) {
					if(property.getCity().equals(filter.getLocation())) 
						propList.add(property);
				}
				if(!property.getType().equals("-")) {
					if(property.getType().equals(filter.getType())) 
						propList.add(property);
				}
				if(!filter.getMaxPrice().equals("-") && !filter.getMinPrice().equals("-")) {
					if(Integer.parseInt(filter.getMaxPrice()) > Integer.parseInt(property.getPropPrice())  &&  Integer.parseInt(filter.getMinPrice()) < Integer.parseInt(property.getPropPrice())) 
						propList.add(property);
				}
				
			/*
			 * if(property.getCity().equals(filter.getLocation()) &&
			 * (filter.getType().equals(property.getType())))
			 * if((Integer.parseInt(filter.getMinPrice()) <
			 * Integer.parseInt(property.getPropPrice())) &&
			 * (Integer.parseInt(filter.getMaxPrice()) >
			 * Integer.parseInt(property.getPropPrice())) ) propList.add(property);
			 */
			}
			return propList;
	}

	public User updateUser(String id, User user) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User updatedUser=em.merge(user);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return updatedUser;
        
		/*
		 * Map<String, User> temp = StaticDB.getUserMap(); temp.put(id, user);
		 * StaticDB.setUserMap(temp); return user;
		 */
	}

	public boolean updatePassword(String id, String newPassword) {
        emf = Persistence.createEntityManagerFactory("RealEstate3");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        user.setPassword(newPassword);
        User updatedUser=em.merge(user);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return updatedUser!=null;
        
		/*
		 * Map<String, User> temp = StaticDB.getUserMap(); try {
		 * //System.out.println(">"+id+"<"); temp.get(id).setPassword(newPassword);
		 * StaticDB.setUserMap(temp); }catch(Exception e) { throw new
		 * UserException("Some-thing went wrong while updating password.",e); } return
		 * true;
		 */
	}

	@Override
	public Property getPropertyById(String propId) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Property property = em.find(Property.class, propId);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return property;
	}

}
