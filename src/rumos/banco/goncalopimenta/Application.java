package rumos.banco.goncalopimenta;

import java.time.LocalDate;
import java.time.Year;
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
	private static final int MANAGE_MONEY = 7;
	private static final int EDIT_BANK_CARDS = 8;

	private static final int EDIT_CUSTOMER_BY_NAME_AND_PASSWORD = 1;
	private static final int EDIT_CUSTOMER_BY_TAXID = 2;
	private static final int EDIT_CUSTOMER_BY_ID = 3;

	private static final int CREATE_DEBIT_CARD = 1;
	private static final int DELETE_DEBIT_CARD = 2;
	private static final int CREATE_CREDIT_CARD = 3;
	private static final int DELETE_CREDIT_CARD = 4;

	private static final int DEPOSIT_MONEY = 1;
	private static final int WITHDRAW_MONEY = 2;
	private static final int TRANSFER_MONEY = 3;

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
				// Show costumer by name
				break;
			case FIND_CUSTOMER_BY_TAXID:
				showCustomerByTaxId();
				// Show costumer by taxId
				break;
			case SHOW_ALL_CUSTOMERS:
				showAllCustomers();
				// Show all costumers
				break;
			case EDIT_CUSTOMER:
				editCustomer();
				// Edit customer by ID
				break;
			case DELETE_CUSTOMER:
				deleteCustomerById();
				// Delete customer by ID
				break;
			case MANAGE_MONEY:
				moneyManagement();
				// Deposit balance
				break;
			case EDIT_BANK_CARDS:
				editBankCards();
				// Edit cards
				break;

			case EXIT:
				System.out.println(GOODBYE);
				break;
			default:
				System.err.println(INVALID_OPTION + " in DisplayMenu");
				break;
			}
		} while (option != 0);
	}

	/******************************************************************************
	 * Bank Cards
	 ******************************************************************************/
	private static void editBankCards() {
		Integer option;
		Customer customer;

		customer = checkNameAndPassword();

		if (customer == null) {
			return;
		}

		do {

			displayBankCardsMenu();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_DEBIT_CARD:
				createDebitCard(customer);
				// Add Debit card
				break;
			case DELETE_DEBIT_CARD:
				deleteDebitCard(customer);
				// Remove Debit card
				break;
			case CREATE_CREDIT_CARD:
				createCreditCard(customer);
				// Add Credit Card
				break;
			case DELETE_CREDIT_CARD:
				deleteCreditCard(customer);
				// Remove Credit Card
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in EditBankCards");
				break;
			}
		} while (option != 0);
	}

	private static void deleteDebitCard(Customer customer) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (customer.isDebitCard()) {
				customer.setDebitCard(false);
				System.out.println("Debit card removed");
				return;
			} else {
				System.err.println(NO_TAKE_DEBIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	private static void deleteCreditCard(Customer customer) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (customer.isCreditCard()) {
				customer.setCreditCard(false);
				System.out.println("Credit card removed");
				return;
			} else {
				System.err.println(NO_TAKE_CREDIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	private static void createDebitCard(Customer customer) {
		System.out.println("Do you whish to create a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!customer.isDebitCard()) {
				customer.setDebitCard(true);
				return;
			} else {
				System.err.println(NO_ADD_DEBIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	private static void createCreditCard(Customer customer) {
		System.out.println("Do you whish to create a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!customer.isCreditCard()) {
				customer.setCreditCard(true);
				return;
			} else {
				System.err.println(NO_ADD_CREDIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	/******************************************************************************
	 * Money movement
	 ******************************************************************************/

	private static void moneyManagement() {
		Integer option;
		Customer customer;

		customer = checkNameAndPassword();

		if (customer == null) {
			return;
		}

		do {
			displayMoneyManagement();
			option = scanner.nextInt();
			switch (option) {
			case DEPOSIT_MONEY:
				depositMoney(customer);
				break;
			case WITHDRAW_MONEY:
				withdrawMoney(customer);
				break;
			case TRANSFER_MONEY:
				transferMoney(customer);
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in moneyManagement");
				break;
			}
		} while (option != 0);

	}

	private static void depositMoney(Customer customer) {

		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		customer.setHolderAccountBalance(customer.getHolderAccountBalance() + Math.abs(amount));
		return;
	}

	private static void withdrawMoney(Customer customer) {

		System.out.println("Please indicate the amount of money you wish to take");
		Double amount = scanner.nextDouble();

		if ((customer.getHolderAccountBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}
		customer.setHolderAccountBalance(customer.getHolderAccountBalance() - Math.abs(amount));
		System.out.printf("\nAt the moment you have %f on your account\n", customer.getHolderAccountBalance());
		return;
	}

	private static void transferMoney(Customer customer) {

		Double amount;
		String accountToTransfer;

		System.out.println("Please indicate the amount of money you wish to transfer");
		amount = scanner.nextDouble();

		if (customer.getHolderAccountBalance() - Math.abs(amount) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}

		System.out.println("Please indicate to which account do you want to transfer?");
		accountToTransfer = scanner.next();
		for (Customer customerToTransfer : customers) {
			if(customerToTransfer == null) break;
			if (customerToTransfer.getAccountHolderNumber().equals(accountToTransfer)) {
				System.out.printf("\nThe account belongs to Name: %s, TaxId: %s and Account Number: %s\n",
						customerToTransfer.getName(), customerToTransfer.getTaxId(),
						customerToTransfer.getAccountHolderNumber());
				customerToTransfer.setHolderAccountBalance(customerToTransfer.getHolderAccountBalance() + amount);
				customer.setHolderAccountBalance(customer.getHolderAccountBalance() - Math.abs(amount));
				System.out.println("Presenting both accounts values");
				System.out.println("From: " + customer.toString());
				System.out.println("To: " + customerToTransfer.toString());
				return;
			}

		}
		System.err.println("There is no account with that number.");
		return;
	}

	/******************************************************************************
	 * Edit customers methods
	 ******************************************************************************/

	private static void editCustomer() {
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

	private static void editCustomerByNameAndPassword() {
		System.out.println("Please write the name of the customer");
		String name = scanner.next();
		System.out.println("Please write the Password of the customer");
		String password = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getName().equals(name) && customers[i].getPassword().equals(password)) {
				customers[i] = editCustomerDetails(customers[i]);
				return;
			}
		}
		System.err.println(INVALID_NAME_OR_PASSWORD);
		return;

	}

	private static void editCustomerByTaxID() {
		System.out.println("Please write the taxID of the customer, in order to edit it");
		String taxId = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getTaxId() == taxId) {
				customers[i] = editCustomerDetails(customers[i]);
				// Edit Customer
				return;
			}
		}
		System.err.println(INVALID_TAXID);
		return;
	}

	private static void editCustomerByID() {
		System.out.println("Please write the ID of the customer, in order to edit it");
		Integer id = scanner.nextInt();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == id) {
				customers[i] = editCustomerDetails(customers[i]);
				// Edit Customer
				return;
			}
		}
		System.err.println(INVALID_ID);
		return;
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
				// Change Name
				break;
			case 2:
				System.out.println("What is the new password?");
				customer.setPassword(scanner.next());
				// Change password
				break;
			case 3:
				System.out.println("What is the new email?");
				customer.setEmail(scanner.next());
				// change email
				break;
			case 0:
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
	 * Show customers methods
	 ******************************************************************************/

	private static void showCustomersByName() {
		System.out.println("What is the customer name?");
		String name = scanner.next();

		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getName().toLowerCase().equals(name.toLowerCase())) {
				for (int j = (i + 1); j < customers.length; j++) {
					if (customers[j].getName().toLowerCase().equals(name.toLowerCase())) {
						System.err.println(
								"There is another client with the same name. Its necessary to search by the taxId.");
						System.out.printf("Presenting the both clients with the same name \nFirst %s \nSecond %s",
								customers[i], customers[j]);
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
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] != null) {
				System.out.println(customers[i].toString());
			}
		}
	}

	private static void showCustomerByTaxId() {
		System.out.println("What is costumer by taxID ?");
		String taxId = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].equals(null))
				continue;
			if (customers[i].getTaxId().equals(taxId)) {
				System.out.println("The costumer is: " + customers[i].toString());
				return;
			}
		}
	}

	/******************************************************************************
	 * Create customer on the array
	 ******************************************************************************/

	private static void createNewCustomer() {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] == null) {
				customers[i] = populateEmptyDatabase();
				System.out.println(CUSTOMER_CREATED);
				return;
			}
		}
		System.err.println("The Clients vector is full");
	}

	/******************************************************************************
	 * Delete customer
	 ******************************************************************************/

	private static void deleteCustomerById() {
		System.out.println("Please write the ID of the customer, in order to delete it");
		Integer id = scanner.nextInt();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == id) {
				customers[i] = null;
				System.out.println(CUSTOMER_DELETED);
				return;
			}
		}
		System.out.println(CUSTOMER_NOT_FOUND);
	}

	/******************************************************************************
	 * Check if there is already an taxID
	 * 
	 * @param taxId
	 * @return
	 ******************************************************************************/
	private static Boolean taxIDalreadyExists(String taxId) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] != null) {
				if (taxId.equals(customers[i].getTaxId()))
					return true;
			}
		}
		return false;
	}

	/******************************************************************************
	 * Populate the array Customers
	 * 
	 * @return
	 ******************************************************************************/

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
		String taxId = scanner.next();
		if (!taxIDalreadyExists(taxId))
			newCustomer.setTaxId(taxId);
		else
			System.out.println("taxID already exists"); // Melhorar esta parte do codigo, necessario fazer um loop para
														// garantir q o TaxID é bem introduzido

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
			System.out.println("The customer is to young to open bank account");// Melhorar esta parte do codigo,
																				// necessario fazer um loop para
																				// garantir q o year seja bem
																				// introduzido

		System.out.println("Please set the number of the account (5 digits)");
		newCustomer.setAccountHolderNumber(scanner.next());

		System.out.println("Please set the customer balance on its main account");
		newCustomer.setHolderAccountBalance(scanner.nextDouble());
		
		System.out.println("Please indicate how many secondary accounts do you want to have");
		Integer count = scanner.nextInt();
		String secondaryAccount;
		for (int i = 0; i < count; i++) {
			System.out.printf("\nPlease indicate the %dº Account Number of the Secondary accounts to be associated ",i+1);
			secondaryAccount = scanner.next();
			newCustomer.getSecundaryAccountNumber()[i] = secondaryAccount;
		}

		System.out.println("Do you which to have a Debit Card? y/n");
		if (scanner.next().equals("y"))
			newCustomer.setDebitCard(true);
		else
			newCustomer.setDebitCard(false);

		System.out.println("Do you which to have a Credit Card? y/n");
		if (scanner.next().equals("y"))
			newCustomer.setDebitCard(true);
		else
			newCustomer.setDebitCard(false);

		return newCustomer;
	}

	/******************************************************************************
	 * check if the name and password are correct At the moment its unused since we
	 * need to have the index to know who is the customer
	 * 
	 * @return
	 ******************************************************************************/
	private static Customer checkNameAndPassword() {
		System.out.println("Please write your name");
		String name = scanner.next();
		System.out.println("Please write your Password");
		String password = scanner.next();

		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
				return customer;
			}
		}
		System.err.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}

	/******************************************************************************
	 * Display menus
	 ******************************************************************************/
	private static void displayMenu() {

		System.out.println("###################### " + MOTD + " ######################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Create new customer");
		System.out.println("2 - Show customer by name");
		System.out.println("3 - Show customer by taxId");
		System.out.println("4 - Show all customers");
		System.out.println("5 - Edit customer");
		System.out.println("6 - Delete customer by Id");
		System.out.println("7 - Manage money on account");
		System.out.println("8 - Edit bank cards");
		System.out.println("###########################################################################");

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

	private static void displayBankCardsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Debit Card");
		System.out.println("2 - Delete Debit Card");
		System.out.println("3 - Create Credit Card");
		System.out.println("4 - Delete Credit Card");
		System.out.println("###########################################################################");
	}

	private static void displayMoneyManagement() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Deposit money on your account");
		System.out.println("2 - Withdraw money from your account");
		System.out.println("3 - Transfer money to another account");
		System.out.println("###########################################################################");
	}

}
