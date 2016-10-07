package model;

import java.util.List;

public interface ICustomerDAO {
	void createRecord(Customer customer);
	Customer retrieveCustomerByID(int id);
	List<Customer> retrieveAllRecords();
	void updateRecord(Customer updatedCustomer);
	void deleteRecord(int id);
	void deleteRecord(Customer customer);
	
	@Override
	String toString();
}
