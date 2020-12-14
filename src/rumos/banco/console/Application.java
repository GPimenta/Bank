package rumos.banco.console;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

import rumos.banco.model.Customer;
import rumos.banco.service.CustomerService;

public class Application {
	private static final int EXIT = 0;
	private static final int CREATE_NEW_CUSTOMER = 1;
	private static final int SHOW_CUSTOMER = 2;
	private static final int EDIT_CUSTOMER_ACCOUNTS = 3;
	private static final int TO_DECIDE_2 = 4;
	private static final int EDIT_CUSTOMER_PERSONAL_DETAILS = 5;
	private static final int DELETE_CUSTOMER = 6;
	private static final int MANAGE_MONEY = 7;
	private static final int EDIT_BANK_CARDS = 8;

	private static final int SHOW_CUSTOMER_BY_NAME = 1;
	private static final int SHOW_CUSTOMER_BY_TAXID = 2;
	private static final int SHOW_ALL_CUSTOMER = 3;

	private static final int EDIT_CUSTOMER_BY_NAME_AND_PASSWORD = 1;
	private static final int EDIT_CUSTOMER_BY_TAXID = 2;
	private static final int EDIT_CUSTOMER_BY_ID = 3;

	private static final int CHANGE_NAME = 1;
	private static final int CHANGE_PASSWORD = 2;
	private static final int CHANGE_EMAIL = 3;

	private static final int CREATE_DEBIT_CARD = 1;
	private static final int DELETE_DEBIT_CARD = 2;
	private static final int CREATE_CREDIT_CARD = 3;
	private static final int DELETE_CREDIT_CARD = 4;

	private static final int DEPOSIT_MONEY_ON_HOLDER_ACCOUNT = 1;
	private static final int DEPOSIT_MONEY_ON_SECONDARY_ACCOUNT = 2;
	private static final int WITHDRAW_MONEY_ON_HOLDER_ACCOUNT = 3;
	private static final int WITHDRAW_MONEY_ON_SECONDARY_ACCOUNT = 4;
	private static final int TRANSFER_MONEY = 5;
	private static final int CHECK_ACCOUNT_HISTORY = 6;

	private static final String MOTD = "Welcome to Rumos Digital Bank";
	private static final String TITLE = "Rumos Digital Bank";
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
	private static final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private static final String NO_ADD_CREDIT_CARD = "You already have a Credit Card";
	private static final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private static final String NO_TAKE_CREDIT_CARD = "You do not have a Credit Card";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";
	private static final String NO_MONEY_TO_REMOVE = "You are removing an amount bigger than what you own";
	
	private static Scanner scanner = new Scanner(System.in);
	private static Integer option;
	private static SecureRandom random = new SecureRandom();
	
	
	
	public static void main(String[] args) {
//		do {
//			displayMenu();
//			option = scanner.nextInt();
//
//			switch (option) {
//			case CREATE_NEW_CUSTOMER:
//				createNewCustomer();
//				break;
//			case SHOW_CUSTOMER:
//				showCustomer();
//				// Show costumer by name
//				break;
//			case EDIT_CUSTOMER_ACCOUNTS:
//				editSecondaryAccounts();
//				// Show costumer by taxId
//				break;
//			case TO_DECIDE_2:
////				showAllCustomers();
//				// Show all costumers
//				break;
//			case EDIT_CUSTOMER_PERSONAL_DETAILS:
//				editCustomerPersonalDetails();
//				// Edit customer by ID
//				break;
//			case DELETE_CUSTOMER:
//				deleteCustomerById();
//				// Delete customer by ID
//				break;
//			case MANAGE_MONEY:
//				moneyManagement();
//				// Deposit balance
//				break;
//			case EDIT_BANK_CARDS:
//				editBankCards();
//				// Edit cards
//				break;
//
//			case EXIT:
//				System.out.println(GOODBYE);
//				break;
//			default:
//				System.err.println(INVALID_OPTION + " in DisplayMenu");
//				break;
//			}
//		} while (option != 0);

	}
	/******************************************************************************
	 * Populate the Customers
	 * 
	 * @return
	 ******************************************************************************/

	private static Customer populateCustomer() {
		System.out.println("Creating new client");
		Customer newCustomer = new Customer();
		CustomerService customerService = new CustomerService();
		
		System.out.println("Please set Name");
		newCustomer.setName(scanner.next());
		
		System.out.println("Please set TaxId");
		String taxId = scanner.next();
		if (!customerService.taxIDalreadyExists(taxId))
			newCustomer.setTaxId(taxId);
		else
			System.out.println("taxID already exists");
		
		System.out.println("Please set email");
		newCustomer.setEmail(scanner.next());

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
		
		

		return newCustomer;
	}

	
	
	/******************************************************************************
	 * Display menus
	 ******************************************************************************/
	private static void displayMenu() {

		System.out.println("###################### " + MOTD + " ######################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Create new customer");
		System.out.println("2 - Show customer methods");
		System.out.println("3 - Edit customer accounts");
		System.out.println("4 - TO DECIDE");
		System.out.println("5 - Edit customer personal details");
		System.out.println("6 - Delete customer by Id");
		System.out.println("7 - Manage money on account");
		System.out.println("8 - Edit bank cards");
		System.out.println("###########################################################################");

	}
	
	

}
