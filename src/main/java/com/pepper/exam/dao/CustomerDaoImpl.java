package com.pepper.exam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pepper.exam.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	private SessionFactory sessionFactory; 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();
		tx.rollback();
		return customers;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(customer);
		
		session.getTransaction().commit();
		
		return customer;
	}

	@Override
	public Customer findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, id);
		
		tx.rollback();
		
		return customer;
	}

	@Override
	public Map<String,Object> update(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		String updateHql = "UPDATE Customer SET lastname=:lastname,firstname=:firstname,address=:address WHERE id_customer=:id_customer";
		int status = session.createQuery(updateHql)
			.setString("lastname", customer.getLastname())
			.setString("firstname", customer.getFirstname())
			.setString("address", customer.getAddress())
			.setInteger("id_customer", customer.getId_customer())
			.executeUpdate();
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("action", "update");
		result.put("statuscode", status);
		result.put("Customer", session.createCriteria(Customer.class).add(Restrictions.eqOrIsNull("id_customer", customer.getId_customer())).list());
		
		session.getTransaction().commit();
		
		return result;
	}

	@Override
	public Map<String,Object> delete(int id_customer) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Map<String,Object> result = new HashMap<String, Object>();
			
		String hql = "DELETE FROM Customer WHERE id_customer=:id_customer";
		
		result.put("action", "Delete");
		result.put("deleted-data",session.createCriteria(Customer.class).add(Restrictions.eqOrIsNull("id_customer", id_customer)).list());
		result.put("statuscode", session.createQuery(hql).setInteger("id_customer", id_customer).executeUpdate());
		
		session.getTransaction().commit();
		
		return result;
	}

}
