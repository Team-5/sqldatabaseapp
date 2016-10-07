package model.datastore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.ICustomerDAO;

/**
 * EmployeeDAO (Data Access Object) handles all interactions with the data
 * store. This version uses a MySQL database to store the data. It is
 * multiuser safe.
 * 
 * @author Jason Whiting
 * @version 2016-10-07
 */
public class CustomerDAO implements ICustomerDAO{

	@Override
	public void createRecord(Customer customer) {
		
	}

	@Override
	public Customer retrieveCustomerByID(int id) {
		return null;
	}

	@Override
	public List<Customer> retrieveAllRecords() {
		return null;
	}

	@Override
	public void updateRecord(Customer updatedCustomer) {
		
	}

	@Override
	public void deleteRecord(int id) {
		
	}

	@Override
	public void deleteRecord(Customer customer) {
		
	}

}
