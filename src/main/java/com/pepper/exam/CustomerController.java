package com.pepper.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pepper.exam.dao.CustomerDaoImpl;
import com.pepper.exam.model.Customer;
import com.pepper.exam.service.CustomerService;

@RestController
@RequestMapping(value="customer/")
public class CustomerController {
	
	@Autowired
	private final CustomerService cdi;
	
	public CustomerController(final CustomerService emp) {
		this.cdi = emp;
	}

	@RequestMapping(value="show-all")
	public List<Customer> all(Model model) {
		
		return cdi.findAll();
	}
	
	@RequestMapping(value="remove",method = RequestMethod.GET)
	public Map<String,Object> remove(@ModelAttribute(value="id_customer") int customer_id) {
		
		return cdi.delete(customer_id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	public Map<String,Object> update(Customer customer) {
		
		return cdi.update(customer);
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public Customer addCustomer(@RequestParam("lastname") String lastname,@RequestParam("firstname") String firstname, @RequestParam("address") String address,@RequestParam("id_customer") int id_customer) {
		
		int idCustomer = id_customer;
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		
		if(idCustomer == 0) {
			customer.setId_customer(0);
		}else {
			customer.setId_customer(id_customer);
		}
		
		return cdi.addCustomer(customer);
	}
	
	@RequestMapping(value="get-customer-info",method=RequestMethod.GET)
	public Customer findCustomerById(@RequestParam("id_customer") int id_customer) {
		
		return cdi.findById(id_customer);
	}
	
}
