package com.cg.capstore.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.service.ICustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;

	
	@GetMapping("/helloCust")
	public ResponseEntity<Object> checkWorking(){
		return new ResponseEntity<Object>("Hello Customer..", HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/myorders/{username}")
	public ResponseEntity<Set<Order>> getOrders(@PathVariable("username")String username) throws Exception{
		return new ResponseEntity<Set<Order>>(customerService.getOrders(username), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/updateStatus/{username}/{orderId}/{status}")
	public ResponseEntity<Boolean> updateStatus(@PathVariable("username")String username,@PathVariable("orderId")Integer orderId,@PathVariable("status")String status) throws Exception{
		return new ResponseEntity<Boolean>(customerService.updateStatus(username,orderId,status), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getStatus/{username}/{orderId}")
	public ResponseEntity<String> getStatus(@PathVariable("username")String username,@PathVariable("orderId")Integer orderId) throws Exception{
		return new ResponseEntity<String>(customerService.getStatus(username,orderId), HttpStatus.OK);
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/myorders/{orderId}")
//	public ResponseEntity<Boolean> isCoupounApplied(@PathVariable("orderId")Integer orderId) throws Exception{
//		return new ResponseEntity<Boolean>(customerService.isCoupounApplied(orderId), HttpStatus.OK);
//	}
	
	
	
	//Shivam
	@GetMapping(value = "/loginVerify/{username}/{securityQuestion}/{securityAnswer}")
	public String loginAuthentication(@PathVariable("username") String username,
			@PathVariable("securityQuestion") String securityQuestion,
			@PathVariable("securityAnswer") String securityAnswer) {
//		logger.info("In UserController at function UsernameAuthentication");
		return customerService.UsernameAuthentication(username, securityQuestion, securityAnswer);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/changePassword/{username}/{oldPassword}/{newPassword}")
	public ResponseEntity<Boolean> checkPassword(@PathVariable("username") String username,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword) {
		System.out.println("controller hit");
		return new ResponseEntity<Boolean>(customerService.changePassword(username, oldPassword, newPassword),
				HttpStatus.OK);

	}
	
	//Sagar
	@GetMapping("/viewAddress/{userName}") 
	public List<Address> viewAddress(@PathVariable String userName)// for viewing all the addresses saved by a particular customer
	{
		
		return customerService.viewAddress(userName);
	}
	
	@GetMapping("/deleteAddress/{addressId}") 
	public boolean deleteAddress(@PathVariable Integer addressId)// for deleting address from customer end
	{
	
		return customerService.deleteAddress(addressId);
	}
	
	@PostMapping("/addAddress/{userName}")
	public boolean addAddress(@RequestBody Address add, @PathVariable String userName) // for adding address by customer
	{
		return customerService.addAddress(add,userName);
	}

	//Prajakta
//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/getUserDetails/{username}")
	public ResponseEntity<CustomerDetails> getUserDetails(@PathVariable("username") String username)
	{

		return new ResponseEntity<CustomerDetails>(customerService.getUserDetails(username),HttpStatus.OK);
	}
	
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping	(value="/editUser")
	public String editUser(@RequestBody CustomerDetails customer)
	{
		return customerService.editUser(customer);
	}
}
