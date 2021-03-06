package com.cg.capstore.dao;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;

public interface IMerchantDao {

	List<MerchantDetails> getMerchants() throws Exception;

	MerchantDetails getMerchantInfo(String username);

	Set<Order> getMerchantOrders(String username);

	Order acceptMerchantOrder(long orderId, String status);

	void activateMerchant(MerchantDetails merchant) throws Exception;

	void deActivateMerchant(MerchantDetails merchant) throws Exception;

	MerchantDetails findMerchantByUsername(String username) throws Exception;

	Set<Product> getMerchantProducts(String username);

	void activateProduct(Product product) throws Exception;

	void inActivateProduct(Product product) throws Exception;

	Product findProductById(int id) throws Exception;

}
