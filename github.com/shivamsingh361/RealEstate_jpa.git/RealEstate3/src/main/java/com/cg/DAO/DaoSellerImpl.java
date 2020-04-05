package com.cg.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Set;

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
		@SuppressWarnings("unchecked")
		List<Property> propList = em.createQuery("from Property",Property.class).getResultList(); 		
		//System.out.println(propList);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("> New Property Added!");
		
		/*
		 * Map<String, Property> temp = StaticDB.getPropertyMap();
		 * temp.put(property.getPropId(), property); StaticDB.setPropertyMap(temp);
		 * List<Property> propList = new ArrayList<Property>(); Set<Entry<String,
		 * Object>> prop = temp.entrySet(); List<Property> propList = new
		 * ArrayList<Property>(); for(Entry<?, ?> tem : prop){
		 * propList.add((Property)tem.getValue()); }
		 */
		return propList;
	}

	@Override
	public List<Property> deleteProperty(String propId) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Property deleteProperty = em.find(Property.class, propId);
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
		/*
		 * Map<String, Property> temp = StaticDB.getPropertyMap(); 
		 * temp.remove(propId);  StaticDB.setPropertyMap(temp); 
		 * Set<Entry<String, Object>> prop = temp.entrySet(); List<Property>
		 * propList = new ArrayList<Property>();  for(Entry<?, ?> tem : prop){
		 * propList.add((Property)tem.getValue());  }
		 */		
		return propList;
	}

}
