package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.Customer;
import model.ICustomerDAO;

/**
 * EmployeeDAO (Data Access Object) handles all interactions with the data
 * store. This version uses a file to store the data. It is not multiuser safe.
 *
 * @author Jason Whiting
 * @version 2016-10-14
 *
 */
public class CustomerDAO implements ICustomerDAO {

	protected String fileName = null;
	protected final List<Customer> myList;

	public CustomerDAO() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("res/file/db.properties"));
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Customer customer) {
		myList.add(customer);
		writeList();

	}

	@Override
	public Customer retrieveCustomerByID(int id) {
		for (Customer Customer : myList) {
			if (Customer.getId() == id) {
				return Customer;
			}
		}
		return null;
	}

	@Override
	public List<Customer> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Customer updatedCustomer) {
		for (Customer customer : myList) {
			if (customer.getId() == updatedCustomer.getId()) {
				customer.setLastName(updatedCustomer.getLastName());
				customer.setFirstName(updatedCustomer.getFirstName());
				customer.setHomePhone(updatedCustomer.getHomePhone());
				customer.setCity(updatedCustomer.getCity());
				customer.setState(updatedCustomer.getState());
				customer.setAge(updatedCustomer.getAge());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Customer customer : myList) {
			if (customer.getId() == id) {
				myList.remove(customer);
				break;
			}
		}
		writeList();

	}

	@Override
	public void deleteRecord(Customer customer) {
		myList.remove(customer);
		writeList();
	}

	private void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String firstName = data[1];
				String lastName = data[2];
				String homePhone = data[3];
				String city = data[4];
				String state = data[5];
				int age = Integer.parseInt(data[6]);
				Customer customer = new Customer(id, firstName, lastName, homePhone, city, state, age);
				myList.add(customer);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	private void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Customer customer : myList) {
				writer.write(String.format("%d,%s,%s,%s,%s,%s,%d\n", customer.getId(), customer.getLastName(),
						customer.getFirstName(), customer.getHomePhone(), customer.getCity(), customer.getState(),
						customer.getAge()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Customer customer : myList) {
			sb.append(String.format("%5d : %s, %s, %s, %s, %s, %3d\n", customer.getId(), customer.getFirstName(),
					customer.getLastName(), customer.getHomePhone(), customer.getCity(), customer.getState(),
					customer.getAge()));
		}

		return sb.toString();
	}

    @Override
    public List<Customer> getAgeGroup(int min, int max){
        List<Customer> agegroup = new ArrayList<>();

        for(Customer c: retrieveAllRecords()){
            if(c.getAge() >= min && c.getAge() <=max){
                agegroup.add(c);
            }
        }

        return agegroup;
    }

}