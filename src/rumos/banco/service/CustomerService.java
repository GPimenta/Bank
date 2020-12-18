package rumos.banco.service;

import java.util.ArrayList;
import java.util.Scanner;

import rumos.banco.model.Customer;
import rumos.banco.model.Customer_Old;

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

	
	
	private static ArrayList<Customer> customers = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer id = 0;
	
	public Customer save(Customer customer) {
		id++;
		customer.setId(id);
		customers.add(customer);
		
		return customer;
	}
	
	
	
	

	public void editCustomerPersonalDetails() {
		Integer option;
		do {

			displayEditCustomerMenu();
			option = scanner.nextInt();
			switch (option) {
			case EDIT_CUSTOMER_BY_NAME_AND_PASSWORD:
				editCustomerByName();
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

	public void editCustomerByName() {
		System.out.println("Please write the name of the customer");
		String name = scanner.next();


		for (Customer customer : customers) {
			if (customer.getName().equals(name)) {
				customer = editCustomerDetails(customer);
				return;
			}
		}

		System.err.println(INVALID_NAME_OR_PASSWORD);
		return;

	}

	public void editCustomerByTaxID() {
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

	public void editCustomerByID() {
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

	public Customer editCustomerDetails(Customer customer) {
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
				System.out.println("NOTHING HERE");
//				customer.setPassword(scanner.next());
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
	
	/******************************************************************************
	 * Check if there is already an taxID
	 * 
	 * @param taxId
	 * @return
	 ******************************************************************************/
	public Boolean taxIDalreadyExists(String taxId) {
		
		for (Customer customer : customers) {
			if(customer != null) {
				if(taxId.equals(customer.getTaxId()))
					return true;
			}
		}
		
		return false;
	}
	/******************************************************************************
	 * Show all customers
	 * 
	 *
	 ******************************************************************************/
	public void showCustomersDetails(){
		for(Customer customer : customers) {
			System.out.println("The customer: ");
			System.out.println(customer.toString());
		}
	}
	/******************************************************************************
	 * Show customer using Name and TaxId
	 * 
	 *@return int Id customer
	 ******************************************************************************/
	public Integer findCustomerByNameAndTaxId() {
		String name;
		String taxId;
		
		System.out.println("Please indicate the name of the Customer");
		name = scanner.next();
		System.out.println("Please indicate the taxId of the Customer");
		taxId = scanner.next();
		
		
		for (Customer customer : customers) {
			if(customer.getName().equals(name) && customer.getTaxId().equals(taxId)) {
				System.out.println(customer.toString());
				return customer.getId();
			}
		}
		System.err.println("Customer not found");
		return null;
	}
	/******************************************************************************
	 * Show customer using Id customer
	 * 
	 *
	 ******************************************************************************/
	public Customer findCustomerById(int customerId) {
		for(Customer customer : customers) {
			if(customer.getId().equals(customerId)) {
				System.out.println(customer.toString());
				return customer;
			}
		}
		System.err.println("Customer not found");
		return null;
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
