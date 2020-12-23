package rumos.banco.service;

import java.time.LocalDate;
import java.time.Year;
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

	/******************************************************************************
	 * Save Customer
	 * 
	 * @return
	 ******************************************************************************/
	public Customer save(Customer customer) {
		id++;
		customer.setId(id);
		customers.add(customer);

		return customer;
	}

	/******************************************************************************
	 * Populate the Customers
	 * 
	 * @return
	 ******************************************************************************/
	public Customer populateCustomer() {
		System.out.println("Creating new client");
		Customer newCustomer = new Customer();

		System.out.println("Please set Name");
		newCustomer.setName(scanner.next());

		do {
			System.out.println("Please set TaxId");
			String taxId = scanner.next();
			if (!taxIDalreadyExists(taxId))
				newCustomer.setTaxId(taxId);
			else
				System.out.println("taxID already exists");
		} while (newCustomer.getTaxId().equals(null));

		System.out.println("Please set email");
		newCustomer.setEmail(scanner.next());

		do {
			System.out.print("Customer day of birth ");
			Integer day = scanner.nextInt();
			System.out.print("Customer month of birth ");
			Integer month = scanner.nextInt();
			System.out.print("Customer year of birth ");
			Integer year = scanner.nextInt();
			if ((Year.now().getValue() - year) >= 18)
				newCustomer.setDateOfBirth(LocalDate.of(year, month, day));
			else
				System.out.println("The customer is to young to open bank account");
		} while (newCustomer.getDateOfBirth() == null);
		return newCustomer;
	}
	/******************************************************************************
	 * Delete Customer
	 * 
	 * @return
	 ******************************************************************************/
	public Integer deleteCustomerDetails() {
		System.out.println("Please indicate the name of the customer");
		String customerName;
		customerName = scanner.next();
		System.out.println("Plase indicate the taxId of the customer");
		String taxId;
		taxId = scanner.next();
		
		
		for (Customer customer : customers) {
			if(customer.getName().equalsIgnoreCase(customerName) && customer.getTaxId().equals(taxId)) {
				customers.remove(customer);
				return customer.getId();
			}
		}
		System.err.println("No customer found");
		return null;
		
	}
	
	/******************************************************************************
	 * Edit Customers
	 * 
	 * @return
	 ******************************************************************************/
//	public void editCustomerByName() {
//		System.out.println("Please write the name of the customer");
//		String name = scanner.next();
//
//		for (Customer customer : customers) {
//			if (customer.getName().equals(name)) {
//				customer = editCustomerDetails(customer);
//				return;
//			}
//		}
//
//		System.err.println(INVALID_NAME_OR_PASSWORD);
//		return;
//
//	}
//
//	public void editCustomerByTaxID() {
//		System.out.println("Please write the taxID of the customer, in order to edit it");
//		String taxId = scanner.next();
//
//		for (Customer customer : customers) {
//			if (customer.getTaxId().equals(taxId)) {
//				customer = editCustomerDetails(customer);
//				return;
//			}
//		}
//		System.err.println(INVALID_TAXID);
//		return;
//	}
//
//	public void editCustomerByID() {
//		System.out.println("Please write the ID of the customer, in order to edit it");
//		Integer id = scanner.nextInt();
//
//		for (Customer customer : customers) {
//			if (customer.getId().equals(id)) {
//				customer = editCustomerDetails(customer);
//			}
//		}
//		System.err.println(INVALID_ID);
//		return;
//	}


	/******************************************************************************
	 * Check if there is already an taxID
	 * 
	 * @param taxId
	 * @return
	 ******************************************************************************/
	public Boolean taxIDalreadyExists(String taxId) {

		for (Customer customer : customers) {
			if (customer != null) {
				if (taxId.equals(customer.getTaxId()))
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
	public void showCustomersDetails() {
		for (Customer customer : customers) {
			System.out.println("The customer: ");
			System.out.println(customer.toString());
		}
	}

	/******************************************************************************
	 * Show customer using Name and TaxId
	 * 
	 * @return int Id customer
	 ******************************************************************************/
	public Integer findCustomerByNameAndTaxId() {
		String name;
		String taxId;

		System.out.println("Please indicate the name of the Customer");
		name = scanner.next();
		System.out.println("Please indicate the taxId of the Customer");
		taxId = scanner.next();

		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getTaxId().equals(taxId)) {
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
		for (Customer customer : customers) {
			if (customer.getId().equals(customerId)) {
				System.out.println(customer.toString());
				return customer;
			}
		}
		System.err.println("Customer not found");
		return null;
	}



}
