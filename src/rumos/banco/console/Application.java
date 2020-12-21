package rumos.banco.console;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Card;
import rumos.banco.model.Customer;
import rumos.banco.service.AccountService;
import rumos.banco.service.CardService;
import rumos.banco.service.CustomerService;

public class Application {
	private static final int EXIT = 0;

	private static final int CREATE_NEW_CUSTOMER = 1;
	private static final int CHOOSE_THE_PROGRAM = 2;
	private static final int SHOW_ALL_CUSTOMERS = 3;
	private static final int DELETE_CUSTOMER = 4;

	private static final int GO_TO_ONLINE = 1;
	private static final int GO_TO_ATM = 2;

	private static final int EDIT_CUSTOMER_ACCOUNTS = 3;
	private static final int TO_DECIDE_2 = 4;
	private static final int EDIT_CUSTOMER_PERSONAL_DETAILS = 5;
	

	private static final int MANAGE_MONEY = 1;
	private static final int EDIT_BANK_CARDS = 2;

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
	private static CustomerService customerService = new CustomerService();
	private static AccountService accountService = new AccountService();
	private static CardService cardService = new CardService();

	public static void main(String[] args) {
		initiation();
	}

	private static void initiation() {
		do {
			displayMenu();
			option = scanner.nextInt();
			switch (option) {

			case CREATE_NEW_CUSTOMER:
				createFullCustomer();
				// Create customer, account and card
				break;
			case CHOOSE_THE_PROGRAM:
				chooseProgram();
				// Use the program
				break;
			case SHOW_ALL_CUSTOMERS:
				showAllCustomersDetails();
				// show all customer, account and cards
				break;
			case DELETE_CUSTOMER:
				
				// Delete all details of the customer
				break;
			case EXIT:
				System.out.println(GOODBYE);
				break;

			default:
				System.err.println(INVALID_OPTION + " in Initiation");
				break;
			}
		} while (option != 0);
	}

	private static void chooseProgram() {
		do {
			displayMenu();
			option = scanner.nextInt();
			switch (option) {

			case GO_TO_ONLINE:
				chooseOnline();
				// Use the online
				break;
			case GO_TO_ATM:
				chooseATM();
				// Use the ATM
				break;
			case EXIT:
				System.out.println(GOODBYE);
				break;

			default:
				System.err.println(INVALID_OPTION + " in chooseProgram");
				break;
			}
		} while (option != 0);

	}

	private static void chooseATM() {
		do {
			displayMenu();
			option = scanner.nextInt();
			switch (option) {

			case MANAGE_MONEY:
				editBankCards();
				// check account details and cards details

				break;
			case EDIT_BANK_CARDS:
				moneyManagement();
				// Take, put and transfer money
				break;
			case 3:

				// check account history
				break;

			case EXIT:
				System.out.println(PREVIOUS_MENU);

			default:
				System.err.println(INVALID_OPTION + " in chooseATM");
				break;
			}
		} while (option != 0);

	}

	private static void chooseOnline() {

		do {
			displayMenu();
			option = scanner.nextInt();

			switch (option) {
			case 1:
				editCustomerPersonalDetails();
				// edit customer personal details
				break;
			case 2:

//				showCustomer();
//				// Show costumer details
				break;
			case 3:
				// Edit Account
				break;
			case 4:
				// Show Account details
				break;
			case 5:
				// Deposit and transfer money
				break;

			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;
			default:
				System.err.println(INVALID_OPTION + " in chooseOnline");
				break;
			}
		} while (option != 0);

	}

	/******************************************************************************
	 * Edit customer personal details
	 * 
	 * @return
	 ******************************************************************************/

	private static void editCustomerPersonalDetails() {
		Integer option;
		do {

			displayEditCustomerMenu();
			option = scanner.nextInt();
			switch (option) {
			case EDIT_CUSTOMER_BY_NAME_AND_PASSWORD:
				customerService.editCustomerByName();
				break;
			case EDIT_CUSTOMER_BY_TAXID:
				customerService.editCustomerByTaxID();
				break;
			case EDIT_CUSTOMER_BY_ID:
				customerService.editCustomerByID();
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

	/******************************************************************************
	 * Check secondaryAccounts
	 * 
	 * 
	 ******************************************************************************/

	private static void editSecondaryAccounts() {
		Account account = accountService.checkAccountNameAndPassword();
		Integer choose;

		if (account == null) {
			return;
		}

		do {
			displaySecondaryAccountsMenu();

			choose = scanner.nextInt();

			switch (choose) {
			case 1:
				accountService.addSecondaryAccount(account);
				break;
			case 2:
				accountService.deleteSecondaryAccount(account);
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in EditSecundaryAccounts");
				break;
			}

		} while (choose != 0);
	}

	/******************************************************************************
	 * Bank Cards
	 ******************************************************************************/
	private static void editBankCards() {
		Integer option;
		Card card;

//		//Validade if the customer has any card
//		if (findCustomerCard(customerId) == null) {
//			return;
//		}

		card = cardService.checkCardNumberAndPassword();

		if (card == null) {
			return;
		}

		do {

			displayBankCardsMenu();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_DEBIT_CARD:
				cardService.createDebitCard(card);
				// Add Debit card
				break;
			case DELETE_DEBIT_CARD:
				cardService.deleteDebitCard(card);
				// Remove Debit card
				break;
			case CREATE_CREDIT_CARD:
				cardService.createCreditCard(card);
				// Add Credit Card
				break;
			case DELETE_CREDIT_CARD:
				cardService.deleteCreditCard(card);
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

	/******************************************************************************
	 * Money movement
	 * 
	 * 
	 ******************************************************************************/

	private static void moneyManagement() {
		Integer option;
		Account account;

		account = accountService.checkAccountNameAndPassword();

		if (account == null) {
			return;
		}

		do {
			displayMoneyManagementMenu();
			option = scanner.nextInt();
			switch (option) {
			case DEPOSIT_MONEY_ON_HOLDER_ACCOUNT:
				accountService.depositMoneyOnHolderAccount(account);
				break;
			case DEPOSIT_MONEY_ON_SECONDARY_ACCOUNT:
				accountService.depositMoneyOnSecondaryAccount(account);
				break;
			case WITHDRAW_MONEY_ON_HOLDER_ACCOUNT:
				accountService.withdrawMoneyOnHolderAccount(account);
				break;
			case WITHDRAW_MONEY_ON_SECONDARY_ACCOUNT:
				accountService.withdrawMoneyOnSecondaryAccount(account);
				break;
			case TRANSFER_MONEY:
				accountService.transferMoney(account);
				break;
			case CHECK_ACCOUNT_HISTORY:
				accountService.showAccountHistoryMovement(account);
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

	/******************************************************************************
	 * Create full details Customer
	 * 
	 * @return
	 ******************************************************************************/
	private static void createFullCustomer() {
		createNewCustomer();
		createNewAccount();
		CreateNewCard();
	}

	/******************************************************************************
	 * Create Customer
	 * 
	 * @return
	 ******************************************************************************/

	private static void createNewCustomer() {
		Customer customer = new Customer();

		customer = customerService.populateCustomer();
		System.out.println(CUSTOMER_CREATED);
		customerService.save(customer);

		customerService.showCustomersDetails();

	}

	/******************************************************************************
	 * Create Account
	 * 
	 * @return
	 ******************************************************************************/

	private static void createNewAccount() {
		Account account = new Account();

		account = accountService.populateAccount();
		System.out.println(CUSTOMER_CREATED);
		accountService.save(account);

		accountService.showAccountsDetails();

	}

	/******************************************************************************
	 * Create Card
	 * 
	 * @return
	 ******************************************************************************/

	private static void CreateNewCard() {
		Card card = new Card();
		card = cardService.populateCard();
		System.out.println(CUSTOMER_CREATED);
		cardService.save(card);

		cardService.showCardsDetails();
	}

	/******************************************************************************
	 * Show all customer
	 * 
	 * @return
	 ******************************************************************************/
	private static void showAllCustomersDetails() {
		customerService.showCustomersDetails();
		accountService.showAccountsDetails();
		cardService.showCardsDetails();
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

	private static void displayEditCustomerMenu() {
		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("Please choose the several methods to change the credentials of the customer");
		System.out.println("0 - Return to Original Menu");
		System.out.println("1 - By name and password");
		System.out.println("2 - By TaxID");
		System.out.println("3 - By ID");
		System.out.println("###########################################################################");
	}

	private static void displaySecondaryAccountsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Secondary Account");
		System.out.println("2 - Delete Secondary Account");
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

	private static void displayMoneyManagementMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Deposit money on your Holder Account");
		System.out.println("2 - Deposit money on your Secondary Account");
		System.out.println("3 - Withdraw money from your Holder Account");
		System.out.println("4 - Withdraw money from your Secondary Account");
		System.out.println("5 - Transfer money to another account");
		System.out.println("6 - Show history account");
		System.out.println("###########################################################################");
	}

}
