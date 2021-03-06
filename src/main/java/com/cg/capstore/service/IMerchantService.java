package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.Invitation;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

public interface IMerchantService {
	
	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);

	Order acceptMerchantOrder(long orderId, String status);


	void activateMerchant(String username) throws Exception;

	void deActivateMerchant(String username) throws Exception;

	MerchantDetails findMerchantByUsername(String username) throws Exception;


	Set<Product> getMerchantProducts(String username);

	Set<Invitation> getInvites(String username) throws Exception;
	
	public void activateProduct(int id) throws Exception;

	void deActivateProduct(int id) throws Exception;

	Product findProductById(int id) throws Exception;

}
