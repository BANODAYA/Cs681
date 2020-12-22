package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicReference;

public class Customer {
	private AtomicReference<Address> address;
	
	public Customer(Address address) {
		this.address=new AtomicReference<>(address);
	}
	
	public Address getAddress() {
		return address.get();
	}
	
	public void setAddress(Address a) {
		this.address.set(a);
	}

}
