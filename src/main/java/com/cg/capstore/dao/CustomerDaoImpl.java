package com.cg.capstore.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.User;
import com.cg.capstore.exceptions.UserException;
import com.cg.capstore.repository.CustomerRepository;
import com.cg.capstore.repository.UserRepository;

@Repository("CustomerDaoImpl")
public class CustomerDaoImpl implements ICustomerDao{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	UserRepository userRepository;

	User User;
	
	@Override
	public Long countOfCustomers() throws Exception {
		
		return (Long) customerRepository.count();
	}

	@Override
	public Set<Order> getOrders(String username) {
		if(customerRepository.existsById(username)) 
		{
			CustomerDetails cd = customerRepository.getOne(username);
			return cd.getOrders();
		}
		
		
		return null;
	}
	@Override
	public String getStatus(String username,Integer orderId) {
		if(customerRepository.existsById(username)) 
		{
			CustomerDetails customer = customerRepository.getOne(username);
			Set<Order> orders = customer.getOrders();
			for (Order order : orders) {
				if(order.getOrderId()==orderId) {
					System.out.println(order.getTransaction().getCoupon());
					if(order.getTransaction().getCoupon() == null) {
						return "false";
					}
					else {
						return "true";
					}
				}
			}
		}
		return "false";	
	}
	


	@Override
	public boolean updateStatus(String username, Integer orderId, String status) {
		if(customerRepository.existsById(username)) 
		{
			CustomerDetails customer = customerRepository.getOne(username);
			Set<Order> orders = customer.getOrders();
			
			for (Order order : orders) {
				if(order.getOrderId()==orderId) {
					
					Date date = new Date();
					Timestamp timeStamp = new Timestamp(date.getTime());
					
					order.setStatusDate(timeStamp);
					order.setOrderStatus(status);
					customer.setOrders(orders);
					customerRepository.save(customer);
					return true;
				}
			}
		}
		return false;
	}
	
	//Shivam
	@Override
	public String UsernameAuthentication(String username,String securityQuestion,String securityAnswer) {
//		logger.info("In UserDaoImpl at function UsernameAuthentication");
		try {
			User=userRepository.getOne(username);;
			if(User.getSecurityQuestion().equals(securityQuestion)&&User.getSecurityAnswer().equals(securityAnswer)) {
			
			        // chose a Character random from this String 
			        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			                                    + "0123456789"
			                                    + "abcdefghijklmnopqrstuvxyz"; 
			  
			        // create StringBuffer size of AlphaNumericString 
			        StringBuilder sb = new StringBuilder(8); 
			  
			        for (int i = 0; i <=8; i++) { 
			  
			            // generate a random number between 
			            // 0 to AlphaNumericString variable length 
			            int index 
			                = (int)(AlphaNumericString.length() 
			                        * Math.random()); 
			  
			            // add Character one by one in end of sb 
			            sb.append(AlphaNumericString 
			                          .charAt(index)); 
			        } 
			  
			        String randomPass=  sb.toString(); 
//				String pass= User.getPassword();
//				String pass1=pass.replace( pass, randomPass);
				User.setPassword(randomPass);
				userRepository.save(User);
				return randomPass;
			}
			else {
				
				return "Incorrect security question or answer ";
			}
		}catch (UserException e) {
//			logger.error(e.getMessage());
			throw e;
		}
	}
//
	@Override
	public boolean changePassword(String username, String oldPassword, String newPassword) throws Exception {

		try {
			List<User> userList = userRepository.findAll();
			for (User user : userList) {
				if (user.getUsername().equals(username)) {
					if (BCrypt.checkpw(oldPassword, user.getPassword())) {
						String hashNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
						user.setPassword(hashNewPassword);
						userRepository.save(user);
						return true;
					}
				}
			}
		} catch (Exception exception) {
			throw new Exception("Enter correct Password");
		}
		return false;
	}
	
	//Sagar
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Address> viewAddress(String userName)
	{
		String qStr = "SELECT a FROM Address a WHERE username=:uId";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("uId", userName);
		List<Address> add = query.getResultList();
		return add;
	}
	
	@Override
	public boolean deleteAddress(Integer addressId)
	
	{
		
		String qStr = "SELECT a FROM Address a WHERE a.addressId =: addId";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("addId", addressId);
		Address add = query.getSingleResult();
		add.setDeleted(true);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(add);
		return true;
		
	}
	
	@Override
	public boolean addAddress(Address add,String userName)
	{
		String command = "select user from User user where user.username =: puser";
		TypedQuery<User> query2 = entityManager.createQuery(command, User.class);
		query2.setParameter("puser", userName);

		User user = query2.getSingleResult();
		add.setUser(user);
		add.setDeleted(false);
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(add);
		return true;
	}
	
	//Prajakta
	@Override
	public CustomerDetails getUserDetails(String username)
	{
		if(customerRepository.existsById(username)) 
		{
			CustomerDetails cd = customerRepository.getOne(username);
			System.out.println(cd);
			return cd;
		}
		return null;
		
	}

	@Override
	public String editUser(CustomerDetails customer)
	{
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return "updated successfully";
		
	}


	
}
