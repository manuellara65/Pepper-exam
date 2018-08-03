package com.pepper.exam.model;

public class Customer {

	private int id_customer;
	private String Lastname;
	private String Firstname;
	private String address;
	
	public Customer(String lastname,String firstname,String address) {
		this.Lastname = lastname;
		this.Firstname = firstname;
		this.address = address;
	}
	
	public Customer() {
		
	}
	
	private static final long serialVersionUID = -3465813074586302847L;
	
	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}

	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
