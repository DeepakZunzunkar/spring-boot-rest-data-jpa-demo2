package com.dz.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dz.app.entities.Customer;
import com.dz.app.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers(){
		
		List<Customer> findAllCustomer = customerService.findAllCustomer();
		if(findAllCustomer == null || (findAllCustomer != null && findAllCustomer.isEmpty())) {
//			return new ResponseEntity<List<Customer>>(findAllCustomer,HttpStatus.NO_CONTENT);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

//		return new ResponseEntity<List<Customer>>(findAllCustomer,HttpStatus.OK);
//		return ResponseEntity.ok().body(findAllCustomer);
		return ResponseEntity.of(Optional.of(findAllCustomer));
	}

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		try {
			customer = customerService.addCustomer(customer);	
			if(customer==null) {
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		
		try {
			customer = customerService.updateCustomer(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("{cid}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("cid") Integer cid) {

		Customer findByCustomerId = customerService.findByCustomerId(cid);
		if (findByCustomerId == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
//		return new ResponseEntity<Customer>(findByCustomerId,HttpStatus.OK); 
		return ResponseEntity.of(Optional.of(findByCustomerId));
	}

	@DeleteMapping("{cid}")
	public ResponseEntity<Boolean> deleteCustomeByid(@PathVariable("cid") Integer cid) {
		try {
			boolean deleteCustomer = customerService.deleteCustomer(cid);
			if(deleteCustomer) {
				return ResponseEntity.ok().body(deleteCustomer);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
//		return new ResponseEntity<Boolean>(customerService.deleteCustomer(cid), HttpStatus.OK);
	}
}
