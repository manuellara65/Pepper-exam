package com.pepper.exam.service;

import java.util.List;
import java.util.Map;

import com.pepper.exam.model.Customer;

public interface CustomerService {
	public List<Customer> findAll();
	public Customer addCustomer(Customer customer);
	public Customer findById(int id);
	public Map<String,Object> update(Customer customer);
	public Map<String,Object> delete(int customer_id);
}
