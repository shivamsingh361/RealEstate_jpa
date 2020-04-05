package com.cg.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.cg.DTO.Property;

public class DaoSellerImpl implements DaoSeller {
	 private EntityManagerFactory emf;
	 private EntityManager em;
	
	@Override
	public List<Property> addProperty(Property property) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(property);
		List<Property> propList = em.createQuery("from Property",Property.class).getResultList(); 		
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("> New Property Added!");
		return propList;
	}

	@Override
	public List<Property> deleteProperty(String propId) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Property deleteProperty = em.find(Property.class, Integer.parseInt(propId));
		if(deleteProperty != null) {
			em.remove(deleteProperty);
			System.out.println("> Property Deleted!");
		}
		else{
			System.out.println("> Property Dosen't exist!");
		}
		em.getTransaction().commit();
		List<Property> propList = em.createQuery("from Property",Property.class).getResultList(); 
		em.close();
		emf.close();		
		return propList;
	}

}
