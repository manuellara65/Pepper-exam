package com.pepper.exam.dao;

import java.util.List;
import java.util.Map;

import com.pepper.exam.model.Customer;

public interface CustomerDao {
	public List<Customer> findAll();
	public Customer addCustomer(Customer customer);
	public Customer findById(int id);
	public Map<String,Object> update(Customer customer);
	public Map<String,Object> delete(int customer_id);
}
