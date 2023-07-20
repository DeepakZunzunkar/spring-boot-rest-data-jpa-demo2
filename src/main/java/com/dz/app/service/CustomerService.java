package com.dz.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dz.app.entities.Customer;

@Component
public class CustomerService {

	public static List<Customer> customers = null;

	static {
		customers = new ArrayList<Customer>();
		customers.add(new Customer(0,"super-admin","000","vasudevkuttumbkam"));
		customers.add(new Customer(1,"admin","001","pune"));
	}

	public List<Customer> findAllCustomer() {
		return customers;
	}
	
	public Customer addCustomer(Customer customer) {

		Optional<Customer> findFirst = customers.stream().filter(cst -> cst.equals(customer)).findFirst();
		if (findFirst.isPresent()) {
			System.out.println("already present");
			return null;
		} else {
			customers.add(customer);
			return customer;
		}
	}

	public Customer updateCustomer(Customer customer) {

		customers = customers.stream().map(cst -> {
			
			if (cst.getId() == customer.getId()) {
				cst.setName(customer.getName());
				cst.setContact(customer.getContact());
				cst.setAddress(customer.getAddress());
			}
			return cst;
		}).collect(Collectors.toList());

		return customer;
	}

	public boolean deleteCustomer(Integer  cid) {
		int blength=customers.size();
		customers=customers.stream().filter(cst->cst.getId()!=cid).collect(Collectors.toList());
		int alength=customers.size();
		return blength==alength?false:true;
	}

	public Customer findByCustomerId(Integer cid) {

		return customers.stream().filter(cst -> cst.getId()==cid).findFirst().orElse(null);
		
		//raise exception
//		Customer customer=customers.stream().filter(cst -> cst.getId()==cid).findFirst().get();
//		return customer;
	}

	

}
