package rumos.banco.goncalopimenta;

import java.time.LocalDate;
import java.util.Scanner;


public class Application {
	private static final int DATABASE_SIZE = 3;

	private static final int EXIT = 0;
	private static final int CREATE_NEW_CUSTOMER = 1;
	private static final int FIND_CUSTOMER_BY_NAME = 2;
	private static final int FIND_CUSTOMER_BY_TAXID = 3;
	private static final int SHOW_ALL_CUSTOMERS = 4;
	private static final int EDIT_CUSTOMER = 5;
	private static final int DELETE_CUSTOMER = 6;
	private static final int DEPOSIT_MONEY = 7;


	private static final int EDIT_CUSTOMER_BY_NAME_AND_PASSWORD = 1;
	private static final int EDIT_CUSTOMER_BY_TAXID = 2;
	private static final int EDIT_CUSTOMER_BY_ID = 3;



	private static final String MOTD = "Welcome to Rumos Digital Bank";
	private static final String GOODBYE = "Thanks for using Rumos Digital Bank";
	private static final String CUSTOMER_CREATED = "Customer created!";
	private static final String CUSTOMER_DELETED = "Customer deleted!";
	private static final String CUSTOMER_NOT_FOUND = "Customer not found!";
	private static final String DATABASE_IS_FULL = "Database is full!";
	private static final String DATABASE_IS_EMPTY = "Database is empty!";
	private static final String TAXID_ALREADY_EXISTS = "Customer Tax Id already exists!";
	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
	private static final String INVALID_TAXID = "Incorrect TaxID";
	private static final String INVALID_ID = "Incorrect ID";




	private static Customer[] customers = new Customer[DATABASE_SIZE];
	private static Scanner scanner = new Scanner(System.in);
	private static Integer option;


	public static void main(String[] args) {

		do {
			displayMenu();
			option = scanner.nextInt();

			switch (option) {
			case CREATE_NEW_CUSTOMER:
				createNewCustomer();
				break;
			case FIND_CUSTOMER_BY_NAME:
				showCustomersByName();
				//Show costumer by name
				break;
			case FIND_CUSTOMER_BY_TAXID:
				showCustomerByTaxId();
				//Show costumer by taxId
				break;
			case SHOW_ALL_CUSTOMERS:
				showAllCustomers();
				//Show all costumers
				break;
			case EDIT_CUSTOMER:
				editCustomer();
				//Edit customer by ID
				break;
			case DELETE_CUSTOMER:
				deleteCustomerById();
				//Delete customer by ID
				break;
			case DEPOSIT_MONEY:
				depositMoney();
				//Deposit balance
				break;

			case EXIT:
				System.out.println(GOODBYE);
				break;
			default:
				System.err.println(INVALID_OPTION + " in DisplayMenu");
				break;
			}
		}while(option != 0);
	}


	private static void depositMoney() {
		System.out.println("Please write your name");
		String name = scanner.next();
		System.out.println("Please write your Password");
		String password = scanner.next();
		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getName().equals(name) && customers[i].getPassword().equals(password)) {
				customers[i].setBalance(customers[i].getBalance() + amount);
				return;
			}
		}
		System.out.println(INVALID_NAME_OR_PASSWORD);
	}




	/**
	 * Edit customers methods
	 */

	private static void editCustomer() {
		displayEditCustomer();

		Integer option = scanner.nextInt();

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
			System.out.println("Returning to Menu");
			break;

		default:
			System.err.println(INVALID_OPTION + " in EditCustomer");
			break;
		}
	}

	private static void editCustomerByNameAndPassword() {
		System.out.println("Please write the name of the customer");
		String name = scanner.next();
		System.out.println("Please wirte the Password of the customer");
		String password = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getName().equals(name) && customers[i].getPassword().equals(password)) {
				customers[i] = editCustomerDetails(customers[i]);
				return;
			}
		}
		System.err.println(INVALID_NAME_OR_PASSWORD);

	}

	private static void editCustomerByTaxID() {
		System.out.println("Please write the taxID of the customer, in order to edit it");
		String taxId = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getTaxId() == taxId){
				customers[i] = editCustomerDetails(customers[i]);
				//Edit Customer
				return; 
			}
		}
		System.err.println(INVALID_TAXID);
	}
	private static void editCustomerByID() {
		System.out.println("Please write the ID of the customer, in order to edit it");
		Integer id = scanner.nextInt();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getId() == id){
				customers[i] = editCustomerDetails(customers[i]);
				//Edit Customer
				return; 
			}
		}
		System.err.println(INVALID_ID);
	}

	private static Customer editCustomerDetails(Customer customer) {
		Integer change;
		do {
			displayEditCustomerDetailsMenu();
			change = scanner.nextInt();

			switch (change) {
			case 1:
				System.out.println("What is the new name?");
				customer.setName(scanner.next());
				//Change Name
				break;
			case 2:
				System.out.println("What is the new password?");
				customer.setPassword(scanner.next());
				//Change password
				break;
			case 3:
				System.out.println("What is the new email?");
				customer.setEmail(scanner.next());
				//change email
				break;
			case 0:
				System.out.println("Returning to previous Menu");
				break;
			default:
				System.err.println(INVALID_OPTION +" in EditCustomer");
				break;
			}
		}while(change != 0);

		return customer;
	}

	/**
	 * Show customers methods
	 */

	private static void showCustomersByName() {
		System.out.println("What is the customer name?");
		String name = scanner.next();

		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getName().toLowerCase().equals(name.toLowerCase())) {
				for (int j = (i+1); j < customers.length; j++) {
					if(customers[j].getName().toLowerCase().equals(name.toLowerCase())) {
						System.err.println("There is another client with the same name. Its necessary to search by the taxId.");
						System.out.printf("Presenting the both clients with the same name \nFirst %s \nSecond %s",customers[i],customers[j]);
						showCustomerByTaxId();
						return;
					}
				}
				System.out.println(customers[i].toString());
				return;
			}
		}
		System.err.println(CUSTOMER_NOT_FOUND);
	}

	private static void showAllCustomers() {
		System.out.println("Printing all clients");
		for(int i = 0; i< customers.length; i++) {
			if(customers[i] != null) {
				System.out.println(customers[i].toString());
			}
		}
	}


	private static void showCustomerByTaxId() {
		System.out.println("What is costumer by taxID ?");
		String taxId = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].equals(null)) continue;
			if(customers[i].getTaxId().equals(taxId)) {
				System.out.println("The costumer is: " + customers[i].toString());
				return;
			}
		}
	}
	/**
	 * Create customer on the array
	 */

	private static void createNewCustomer() {
		for(int i = 0; i < customers.length; i++  ) {
			if(customers[i] == null) {
				customers[i] = populateEmptyDatabase();
				System.out.println(CUSTOMER_CREATED);
				return;
			}
		}
		System.err.println("The Clients vector is full");
	}
	/**
	 * Delete customer
	 */

	private static void deleteCustomerById() {
		System.out.println("Please write the ID of the customer, in order to delete it");
		Integer id = scanner.nextInt();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getId() == id){
				customers[i] = null;
				System.out.println(CUSTOMER_DELETED);
				return;
			}
		}
		System.out.println(CUSTOMER_NOT_FOUND);
	}

	/**
	 * Populate the array Customers
	 * @return
	 */

	private static Customer populateEmptyDatabase() {
		System.out.println("Creating new client");
		Customer newCustomer = new Customer();

		System.out.println("Please set Id");
		newCustomer.setId(scanner.nextInt());

		System.out.println("Please set Name");
		newCustomer.setName(scanner.next());

		System.out.println("Please set Password");
		newCustomer.setPassword(scanner.next());

		System.out.println("Please set TaxId");
		newCustomer.setTaxId(scanner.next());

		System.out.println("Please set email");
		newCustomer.setEmail(scanner.next());

		System.out.print("Customer day of birth ");
		Integer day = scanner.nextInt();
		System.out.print("Customer month of birth ");
		Integer month = scanner.nextInt();
		System.out.print("Customer year of birth ");
		Integer year = scanner.nextInt();
		newCustomer.setDateOfBirth(LocalDate.of(year, month, day));

		System.out.println("Please set the customer balance");
		newCustomer.setBalance(scanner.nextDouble());

		return newCustomer;
	}



	/**
	 * Display menus
	 */
	private static void displayMenu() {
		System.out.println( MOTD +" please choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Create new customer");
		System.out.println("2 - Show customer by name");
		System.out.println("3 - Show customer by taxId"); 
		System.out.println("4 - Show all customers"); 
		System.out.println("5 - Edit customer"); 
		System.out.println("6 - Delete customer by Id");
	}
	private static void displayEditCustomerDetailsMenu() {
		System.out.println("What do you which to edit?");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Name of the customer");
		System.out.println("2 - Password of the customer");
		System.out.println("3 - Email of the customer"); 
	}
	private static void displayEditCustomer() {
		System.out.println("Please choose the several methods to change the credentials of the customer");
		System.out.println("0 - Return to Original Menu");
		System.out.println("1 - By name and password");
		System.out.println("2 - By TaxID");
		System.out.println("3 - By ID");
	}
}
