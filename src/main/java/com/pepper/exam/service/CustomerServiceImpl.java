package com.pepper.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pepper.exam.dao.CustomerDao;
import com.pepper.exam.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		return customerDao.addCustomer(customer);
	}

	@Override
	public Customer findById(int id) {
		return customerDao.findById(id);
	}

	@Override
	public Map<String, Object> update(Customer customer) {
		
		return customerDao.update(customer);
	}

	@Override
	public Map<String, Object> delete(int customer_id) {
		
		return customerDao.delete(customer_id);
	}

}
