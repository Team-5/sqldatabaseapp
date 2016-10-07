package model;

/**
 * The Customer class represents a single customer.
 * 
 * @author lisa
 * @version 2016-10-07
 */
public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String homePhone;
	private String city;
	private String state;
	private int age;
	
	public Customer() {
		id = 0;
		firstName = "";
		lastName = "";
		homePhone = "";
		city = "";
		state = "";
		age = 0;
	}

	public Customer(int id, String firstName, String lastName, String homePhone, String city, String state, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhone = homePhone;
		this.city = city;
		this.state = state;
		this.age = age;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getHomePhone() {
		return homePhone;
	}


	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %s, %s, %s, %3d", 
				this.getId(), this.getFirstName(), this.getLastName(), 
				this.getHomePhone(), this.getCity(), this.getState(), 
				this.getAge());
	}
}
