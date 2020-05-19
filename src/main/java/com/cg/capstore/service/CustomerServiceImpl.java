package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.capstore.dao.ICustomerDao;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;

@Transactional
@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public Long countOfCustomers() throws Exception {
		return customerDao.countOfCustomers();
	}

	@Override
	public Set<Order> getOrders(String username) {
		return customerDao.getOrders(username);
	}

	@Override
	public String getStatus(String username,Integer orderId) {
		return customerDao.getStatus(username,orderId);
	}

	@Override
	public boolean updateStatus(String username, Integer orderId, String status) {
		// TODO Auto-generated method stub
		return customerDao.updateStatus(username, orderId, status);
	}
	

	//Shivam	

	@Override
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		try {
			return customerDao.changePassword(username, oldPassword, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	
	@Override
	public String UsernameAuthentication(String username,String securityQuestion,String securityAnswer) {
		return customerDao.UsernameAuthentication(username, securityQuestion, securityAnswer);
	}
	
	//Sagar
	  public List<Address> viewAddress(String username)
	    {
			return customerDao.viewAddress(username);
	    }
		
		@Override
		public boolean deleteAddress(Integer addressId)
		{
			return customerDao.deleteAddress(addressId);
		}
		
		@Override
		public boolean addAddress(Address add,String userName)
		{
			return customerDao.addAddress(add,userName);
		}
		
		//Prajakta
		
		@Override
		public CustomerDetails getUserDetails(String username)
		{
			// TODO Auto-generated method stub
			return customerDao.getUserDetails(username);
		}

		@Override
		public String editUser(CustomerDetails customer)
		{
			// TODO Auto-generated method stub
	          //return dao.editUser(customer);
			return customerDao.editUser(customer);
		}



}
