package model.datastore;

import java.util.List;

import model.Customer;
import model.ICustomerDAO;

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
