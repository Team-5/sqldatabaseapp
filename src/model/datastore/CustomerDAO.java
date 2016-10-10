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
 * store. This version uses a MySQL database to store the data. It is multiuser
 * safe.
 * 
 * @author Jason Whiting
 * @version 2016-10-07
 */
public class CustomerDAO implements ICustomerDAO {

	protected final static boolean DEBUG = true;

	@Override
	public void createRecord(Customer customer) {
		final String QUERY = "insert into customer " + "(id, firstName, lastName, homePhone, state, city, age) "
				+ "VALUES (null, ?, ?, ?, ?, null)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getHomePhone());
			stmt.setString(4, customer.getState());
			stmt.setString(5, customer.getCity());
			stmt.setInt(6, customer.getAge());
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Customer customer : retrieveAllRecords()) {
			sb.append(customer.toString()).append("\n");
		}

		return sb.toString();
	}
}
