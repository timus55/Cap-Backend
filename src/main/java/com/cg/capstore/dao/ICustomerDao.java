package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;

public interface ICustomerDao {
	
	Long countOfCustomers() throws Exception;
	Set<Order>getOrders(String username);
	String getStatus(String username,Integer orderId);
	boolean updateStatus(String username,Integer orderId,String status);


	//Shivam
	public String UsernameAuthentication(String username,String securityQuestion,String securityAnswer);
	boolean changePassword(String username, String oldPassword, String newPassword) throws Exception;

	//Sagar
	public List<Address> viewAddress(String username);
	public boolean deleteAddress(Integer addressId);
	public boolean addAddress(Address add,String username);
	
	//Prajakta
	public CustomerDetails getUserDetails(String username);
	public String editUser(CustomerDetails customer);
}
