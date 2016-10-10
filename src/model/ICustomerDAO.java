package model;

import java.util.List;

/**
 * ICustomerDAO is the interface for the Customer Data Access Object. The
 * interface defines the methods that will be used in all DAO implementations
 * for this application. This program currently has only a database DAO
 * implementation. A file based DAO implementation was deemed unnecessary.
 * 
 * @author Jason Whiting
 * @version 2016-10-07
 */
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
