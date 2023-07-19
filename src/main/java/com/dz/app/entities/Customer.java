package com.dz.app.entities;

public class Customer {

	private Integer id;
	private String name;
	private String contact;
	private String address;

	public Customer() {
	}

	public Customer(Integer id, String name, String contact, String address) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + ", address=" + address + "]";
	}

	@Override
	public boolean equals(Object obj) {

		try {
			Customer customer = (Customer) obj;

			if (customer.getId().equals(id) && customer.getName().equalsIgnoreCase(name)
					&& customer.getContact().equalsIgnoreCase(contact)
					&& customer.getAddress().equalsIgnoreCase(address)) {

				return true;
			} else {
				return false;
			}

		} catch (ClassCastException ce) {
			return false;
		} catch (NullPointerException ne) {
			return false;
		}

	}
}
