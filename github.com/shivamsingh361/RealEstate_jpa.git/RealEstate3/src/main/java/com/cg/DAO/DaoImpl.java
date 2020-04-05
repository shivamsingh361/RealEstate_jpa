package com.cg.DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.DTO.User;
import com.cg.exception.UserException;

public class DaoImpl implements Dao {

	 private EntityManagerFactory emf;
	 private EntityManager em;
	public boolean checkIfUserExist(String id) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.getTransaction().commit();
		em.clear();
		emf.close();
		if(user != null)
			return true;
		else
			return false;
	}

	
	public void register(User user) {
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.clear();
		emf.close();
//		StaticDB.getUserMap().put(user.getLoginId(), user);
	}

	
	public User login(String id, String password) throws UserException{
		emf = Persistence.createEntityManagerFactory("RealEstate3");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.getTransaction().commit();
		em.clear();
		emf.close();
		if(user!=null) {
			if(user.getPassword().equals(password))
				return user;
			else
				throw new UserException("Invalid Password!");
		}
		else
			throw new UserException("User Id dosen't Exist!");
	}
}
