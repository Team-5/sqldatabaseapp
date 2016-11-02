package viewui;

import java.util.List;
import util.Validator;
import java.util.Scanner;
import model.Customer;
import model.ICustomerDAO;
//import model.datastore.file.CustomerDAO;
import model.datastore.mysql.CustomerDAO;

/**
 * CustomerApp is the starting point for running this console-oriented
 * menu-driven customer management program. This program demonstrates only a
 * MySQL based solution.
 * 
 * @authors Lisa Caswell and Jason Whiting
 * @version 2016-10-08
 */
public class CustomerApp {

	ICustomerDAO myList = new CustomerDAO();
	Scanner sc = new Scanner(System.in);

	public CustomerApp() {
		menuloop();
	}

	private void menuloop() {

		int id, age;
		String firstName, lastName, homePhone, city, state;
		String choice = "1";

		while (!choice.equals("0")) {
			System.out.println("\nCustomer App");
			System.out.println("0 = Quit");
			System.out.println("1 = List All Records");
			System.out.println("2 = Create New Record");
			System.out.println("3 = Retrieve Record");
			System.out.println("4 = Update Record");
			System.out.println("5 = Delete Record");
                        System.out.println("6 = Select Age Group");
			choice = Validator.getLine(sc, "number of choice: ", "^[0-6]$");

			switch (choice) {

			case "1":
				System.out.println(myList.toString());
				break;

			case "2":
				id = Validator.getInt(sc, "New customer id: ");
				firstName = Validator.getLine(sc, "First name: ");
				lastName = Validator.getLine(sc, "Last name: ");
				homePhone = Validator.getLine(sc, "Home phone number: ");
				city = Validator.getLine(sc, "City: ");
				state = Validator.getLine(sc, "State: ");
				age = Validator.getInt(sc, "Age: ");
				myList.createRecord(new Customer(id, firstName, lastName, homePhone, city, state, age));
				break;

			case "3":
				id = Validator.getInt(sc, "Employee ID to Retrieve: ");
				System.out.println(myList.retrieveCustomerByID(id));
				break;

			case "4":
				id = Validator.getInt(sc, "New customer id: ");
				firstName = Validator.getLine(sc, "First name: ");
				lastName = Validator.getLine(sc, "Last name: ");
				homePhone = Validator.getLine(sc, "Home phone number: ");
				city = Validator.getLine(sc, "City: ");
				state = Validator.getLine(sc, "State: ");
				age = Validator.getInt(sc, "Age: ");
				myList.updateRecord(new Customer(id, firstName, lastName, homePhone, city, state, age));
				break;

			case "5":
				id = Validator.getInt(sc, "Customer id to delete: ");
				System.out.println(myList.retrieveCustomerByID(id));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					myList.deleteRecord(id);
				}
				break;
                        case "6":
                                int min = Validator.getInt(sc,"Minimum age");
                                int max = Validator.getInt(sc,"Maximum age");
                                List<Customer> group = myList.getAgeGroup(min,max);
                                for(Customer c : group){
                                    System.out.println(c.toString());
                                }
                                System.out.println(group.size());
                            break;
			}
		}
	}

	public static void main(String[] args) {
		new CustomerApp();
	}
}
