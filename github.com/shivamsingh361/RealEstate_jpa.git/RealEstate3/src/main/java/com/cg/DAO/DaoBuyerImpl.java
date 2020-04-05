package com.cg.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.cg.DTO.*;

public class DaoBuyerImpl implements DaoBuyer {
	private EntityManagerFactory emf;
	private EntityManager em;
	public List<Property> searchProperty(Filter filter) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Property> propLst = em.createQuery("from Property",Property.class).getResultList(); 
		List<Property> propList = new ArrayList<Property>();
		
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
	}

	@Override
	public Property getPropertyById(String propId) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Property property = em.find(Property.class, Integer.parseInt(propId));
		em.getTransaction().commit();
		em.close();
		emf.close();
		return property;
	}

}
