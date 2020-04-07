package com.cg.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.DAO.Dao;
import com.cg.DAO.DaoBuyer;
import com.cg.DAO.DaoBuyerImpl;
import com.cg.DAO.DaoImpl;
import com.cg.DAO.DaoSeller;
import com.cg.DAO.DaoSellerImpl;

import com.cg.DTO.Filter;
import com.cg.DTO.InterestList;
import com.cg.DTO.Property;
import com.cg.DTO.User;
import com.cg.DTO.UserType;
import com.cg.exception.PropertyException;
import com.cg.exception.UserException;

public class ServiceImpl implements Service{

	Dao dao = new DaoImpl();
	DaoSeller sellerDao = new DaoSellerImpl();
	DaoBuyer buyerDao = new DaoBuyerImpl();
	User user;

	public User getUser() {
		return user;
	}

	@Override
	public int verifyOTP(String id) {
		if(dao.checkIfUserExist(id)) {
			return (int) (Math.random()*10000);
		}
		else
			return 0;
	}

	@Override
	public boolean forgotPassword(String id, String newPass) {
		return buyerDao.updatePassword(id, newPass);
	}

	@Override
	public String updatePassword(String oldPass, String newPass) {
		if(user.getPassword().equals(oldPass)) {
			if(buyerDao.updatePassword(user.getLoginId(), newPass))
				return "Password Updated";
			else
				return "Server Not Found";
		}
		else
			return "Invalid Old Password!";
	}

	@Override
	public List<Property> userHome() {
		List<Property> properties = new ArrayList<Property>();
		List<Property> allProperties = buyerDao.searchProperty(null);
		if(user.type.equals(UserType.BUYER)) {
			for(Property prop:allProperties ) {
				properties.add(prop);
			}
		}
		else {
			for(Property prop:allProperties) {
				if(user.getLoginId().equals(prop.getOwner().getLoginId()))
					properties.add(prop);
			}
		}
		return properties;
	}

	@Override
	public boolean login(String id, String pass) {
		User temp = null;
		try {
			temp = dao.login(id, pass);
		}catch(UserException e){
			e.getStackTrace();
			e.getMessage();
		}
		if(temp != null){
			user = temp;
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String Register(User user) {
		if(!dao.checkIfUserExist(user.getLoginId())) {
			dao.register(user);
			return "New User Created!";
		}
		else
			return "User Id Already exists. Please Login!";
	}

	@Override
	public List<Property> Search(Filter filter) {
		if(filter.getMaxPrice().equals("-") && filter.getMinPrice().equals("-") && filter.getLocation().equals("-"))
			return buyerDao.searchProperty(null);
		else
			return buyerDao.searchProperty(filter);
	}

	@Override
	public User updateUserProfile(String Name, String contact) {
		if(Name!= null)
			user.setName(Name);
		if(contact!=null)
			user.setPhoneNo(contact);
		return buyerDao.updateUser(user.getLoginId(), user);
	}

	@Override
	public String logout() {
		user = null;
		return "OK";
	}

	@Override
	public List<Property> addProperty(Property prop) {
		prop.setOwner(user);
		return sellerDao.addProperty(prop);
	}

	@Override
	public List<Property> deleteProperty(String propId) {
		try {
			if(user.equals(sellerDao.getProperty(propId).getOwner()))
				return sellerDao.deleteProperty(propId);
		} catch (UserException e) {
			System.out.println("> Please! Enter a correct ID");
			e.printStackTrace();
		}
		return userHome();
	}

	@Override
	public Property viewProperty(String propId) {
		Property prop = null;
		try {
			prop = buyerDao.getPropertyById(propId);
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RealEstate3");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(new InterestList(user.getLoginId(), prop.getCity(), Integer.parseInt(propId)));
		em.getTransaction().commit();
		em.close();
		emf.close();
		return prop;	
	}

}
