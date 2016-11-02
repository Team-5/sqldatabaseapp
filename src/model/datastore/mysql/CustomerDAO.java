package model.datastore.mysql;

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
 * @authors Jason Whiting, Jeremy Wiles, and Willie Scott
 * @version 2016-10-07
 */
public class CustomerDAO implements ICustomerDAO {

	protected final static boolean DEBUG = true;

	/**
	 * The createRecord method will create a single customer object with the
	 * information provided by the user.
	 */
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

	/**
	 * The retrieveCustomerById method will search the database for the id
	 * provided by the user and return the corresponding customer object.
	 */
	@Override
	public Customer retrieveCustomerByID(int id) {

		final String QUERY = "select id, firstName, lastName, homePhone, state, city " + "age from customer where id = "
				+ id;
		Customer cus = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				cus = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("homePhone"), rs.getString("state"), rs.getString("city"), rs.getInt("age"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecodById SQLException: " + ex.getMessage());
		}

		return cus;
	}

	/**
	 * The retrieveAllRecords method will search the database and retrieve all
	 * customer objects.
	 */
	@Override
	public List<Customer> retrieveAllRecords() {

		final List<Customer> myList = new ArrayList<>();
		final String QUERY = "select id, firstName, lastName, homePhone, state, city " + "age from customer";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			ResultSet rs = stmt.executeQuery(QUERY);

			while (rs.next()) {
				myList.add(new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("homePhone"), rs.getString("state"), rs.getString("city"), rs.getInt("age")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	/**
	 * The updateRecord method will search the database for a particular
	 * customer object and replace its information with the information provided
	 * by the user.
	 */
	@Override
	public void updateRecord(Customer updatedCustomer) {
		final String QUERY = "update customer set lastName=?, firstName=?, "
				+ "homePhone=?, state=?, city=?, age=? where id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedCustomer.getFirstName());
			stmt.setString(2, updatedCustomer.getLastName());
			stmt.setString(3, updatedCustomer.getHomePhone());
			stmt.setString(4, updatedCustomer.getState());
			stmt.setString(5, updatedCustomer.getCity());
			stmt.setInt(6, updatedCustomer.getAge());
			stmt.setInt(7, updatedCustomer.getId());
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}

	}

	/**
	 * The deleteRecord method will search the database for either the id or the
	 * customer object provided by the user, depending on which is given. It
	 * will then delete that corresponding customer object.
	 */
	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from customer where customer Id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Customer customer) {
		final String QUERY = "delete from customer where customer Id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, customer.getId());
			if (DEBUG) {
				System.out.println(stmt.toString());
			}
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}
        
        public List<Customer> getAgeGroup(int min, int max){
            List<Customer> agegroup = new ArrayList<>();
            
            for(Customer c: retrieveAllRecords()){
                if(c.getAge() >= min && c.getAge() <=max){
                    agegroup.add(c);
                }
            }
            
            return agegroup;
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
