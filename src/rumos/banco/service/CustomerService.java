package rumos.banco.service;

import java.util.ArrayList;
import java.util.Scanner;

import rumos.banco.model.Customer;

public class CustomerService {
	
	/**
	 * Quick texts
	 */
	private static final int EXIT = 0;

	private static final int EDIT_CUSTOMER_BY_NAME_AND_PASSWORD = 1;
	private static final int EDIT_CUSTOMER_BY_TAXID = 2;
	private static final int EDIT_CUSTOMER_BY_ID = 3;

	private static final int CHANGE_NAME = 1;
	private static final int CHANGE_PASSWORD = 2;
	private static final int CHANGE_EMAIL = 3;

	private static final String TITLE = "Rumos Digital Bank";
	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
	private static final String INVALID_TAXID = "Incorrect TaxID";
	private static final String INVALID_ID = "Incorrect ID";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";

	
	
	private ArrayList<Customer> customers = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);

	public void editCustomerPersonalDetails() {
		Integer option;
		do {

			displayEditCustomerMenu();
			option = scanner.nextInt();
			switch (option) {
			case EDIT_CUSTOMER_BY_NAME_AND_PASSWORD:
				editCustomerByNameAndPassword();
				break;
			case EDIT_CUSTOMER_BY_TAXID:
				editCustomerByTaxID();
				break;
			case EDIT_CUSTOMER_BY_ID:
				editCustomerByID();
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in EditCustomer");
				break;
			}
		} while (option != 0);
	}

	private void editCustomerByNameAndPassword() {
		System.out.println("Please write the name of the customer");
		String name = scanner.next();
		System.out.println("Please write the Password of the customer");
		String password = scanner.next();

		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
				customer = editCustomerDetails(customer);
				return;
			}
		}

		System.err.println(INVALID_NAME_OR_PASSWORD);
		return;

	}

	private void editCustomerByTaxID() {
		System.out.println("Please write the taxID of the customer, in order to edit it");
		String taxId = scanner.next();

		for (Customer customer : customers) {
			if (customer.getTaxId().equals(taxId)) {
				customer = editCustomerDetails(customer);
				return;
			}
		}
		System.err.println(INVALID_TAXID);
		return;
	}

	private void editCustomerByID() {
		System.out.println("Please write the ID of the customer, in order to edit it");
		Integer id = scanner.nextInt();

		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				customer = editCustomerDetails(customer);
			}
		}
		System.err.println(INVALID_ID);
		return;
	}

	private Customer editCustomerDetails(Customer customer) {
		Integer change;
		do {

			displayEditCustomerDetailsMenu();
			change = scanner.nextInt();

			switch (change) {
			case CHANGE_NAME:
				System.out.println("What is the new name?");
				customer.setName(scanner.next());
				// Change Name
				break;
			case CHANGE_PASSWORD:
				System.out.println("What is the new password?");
				customer.setPassword(scanner.next());
				// Change password
				break;
			case CHANGE_EMAIL:
				System.out.println("What is the new email?");
				customer.setEmail(scanner.next());
				// change email
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;
			default:
				System.err.println(INVALID_OPTION + " in EditCustomer");
				break;
			}
		} while (change != 0);

		return customer;

	}

	private static void displayEditCustomerDetailsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("What do you which to edit?");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Name of the customer");
		System.out.println("2 - Password of the customer");
		System.out.println("3 - Email of the customer");
		System.out.println("###########################################################################");
	}

	private static void displayEditCustomerMenu() {
		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("Please choose the several methods to change the credentials of the customer");
		System.out.println("0 - Return to Original Menu");
		System.out.println("1 - By name and password");
		System.out.println("2 - By TaxID");
		System.out.println("3 - By ID");
		System.out.println("###########################################################################");
	}
}
