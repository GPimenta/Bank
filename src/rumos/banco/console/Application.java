package rumos.banco.console;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Card;
import rumos.banco.model.CreditCard;
import rumos.banco.model.Customer;
import rumos.banco.model.DebitCard;
import rumos.banco.repository.ICustomerRepository;
import rumos.banco.repository.InMemCustomerRepository;
import rumos.banco.service.AccountService;
import rumos.banco.service.CardService;
import rumos.banco.service.CustomerService;
import rumos.banco.service.NewCustomerService;

public class Application {
	private static final int EXIT = 0;

	private static final int CREATE_NEW_CUSTOMER = 1;
	private static final int CHOOSE_THE_PROGRAM = 2;
	private static final int SHOW_ALL_CUSTOMERS = 3;
	private static final int DELETE_CUSTOMER = 4;

	private static final int GO_TO_ONLINE = 1;
	private static final int GO_TO_ATM = 2;

	private static final int MANAGE_MONEY = 1;
	private static final int CHECK_ACCOUNT_HISTORY_THROUGH_ATM = 2;

	private static final int EDIT_CUSTOMER_PERSONAL_DETAILS = 1;
	private static final int SHOW_CUSTOMER_DETAILS = 2;
	private static final int EDIT_ACCOUNT_PASSWORD = 3;
	private static final int SHOW_ACCOUNT_DETAILS = 4;
	private static final int ACCOUNT_TRANSFER_MONEY = 5;
	private static final int CHECK_ACCOUNT_HISTORY_THROUGH_ONLINE = 6;
	private static final int EDIT_SECONDARY_ACCOUNTS = 7;
	private static final int EDIT_BANK_CARDS = 8;

	private static final int ADD_SECONDARY_ACCOUNT = 1;
	private static final int DELETE_SECONDARY_ACCOUNT = 2;

	private static final int CHANGE_NAME = 1;
	private static final int CHANGE_EMAIL = 2;

	private static final int EDIT_DEBIT_CARDS = 1;
	private static final int EDIT_CREDIT_CARDS = 2;

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
	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";

	private static Scanner scanner = new Scanner(System.in);
	private static SecureRandom random = new SecureRandom();
	private static CustomerService customerService = new CustomerService();
	private static AccountService accountService = new AccountService();
	private static CardService cardService = new CardService();

	public static void main(String[] args) {
		initiation();
	}

	private static void initiation() {
		Integer option;
		do {
			displayInitiation();
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
				Integer customerId = customerService.deleteCustomerDetails();
				accountService.deleteAccount(customerId);
				cardService.deleteDebitCard(customerId);
				cardService.deleteCreditCard(customerId);

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
		Integer option;
		do {
			displayChooseProgram();
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
		Integer option;
		Card card;
		card = cardService.checkCardNumberAndPassword();

		if (card == null)
			return;

		do {

			displayATM();

			option = scanner.nextInt();
			switch (option) {

			case MANAGE_MONEY:
				moneyManagement(card.getCustomerId());
				// Take, put and transfer money
				break;
			case CHECK_ACCOUNT_HISTORY_THROUGH_ATM:
				accountService.showAccountHistoryMovement(card.getCustomerId());
				// check account history
				break;

			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;
			default:
				System.err.println(INVALID_OPTION + " in chooseATM");
				break;
			}
		} while (option != 0);

	}

	private static void chooseOnline() {
		Integer option;
		Account account;
		account = accountService.checkAccountNameAndPassword();
		if (account == null)
			return;

		do {
			displayOnline();
			option = scanner.nextInt();

			switch (option) {
			case EDIT_CUSTOMER_PERSONAL_DETAILS:
				editCustomerDetails(account.getCustomerId());
				// edit customer personal details
				break;
			case SHOW_CUSTOMER_DETAILS:
				customerService.showCustomer(account.getCustomerId());
//				// Show costumer details
				break;
			case EDIT_ACCOUNT_PASSWORD:
				accountService.editAccountPassword(account.getCustomerId());
				// Edit Account
				break;
			case SHOW_ACCOUNT_DETAILS:
				accountService.showAccountDetails(account.getCustomerId());
				// Show Account details
				break;
			case ACCOUNT_TRANSFER_MONEY:
				accountService.transferMoney(account.getCustomerId());
				break;
			case CHECK_ACCOUNT_HISTORY_THROUGH_ONLINE:
				accountService.showAccountHistoryMovement(account.getCustomerId());
				// Deposit and transfer money
				break;
			case EDIT_SECONDARY_ACCOUNTS:
				editSecondaryAccounts(account.getCustomerId());
				// Deposit and transfer money
				break;
			case EDIT_BANK_CARDS:
				editBankCards(account.getCustomerId());
				// Edit cards
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

	public static void editCustomerDetails(Integer customerId) {
		Integer change;
		Customer customer;

		customer = customerService.findCustomerById(customerId);

		do {

			displayEditCustomerDetailsMenu();
			change = scanner.nextInt();

			switch (change) {
			case CHANGE_NAME:
				System.out.println("What is the new name?");
				customer.setName(scanner.next());
				// Change Name
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

		return;

	}

	/******************************************************************************
	 * Check secondaryAccounts
	 * 
	 * 
	 ******************************************************************************/

	private static void editSecondaryAccounts(Integer customerId) {
		Account account = accountService.findCustomerAccount(customerId);
		Integer choose;

		if (account == null) {
			return;
		}

		do {
			displaySecondaryAccountsMenu();

			choose = scanner.nextInt();

			switch (choose) {
			case ADD_SECONDARY_ACCOUNT:
				accountService.addSecondaryAccount(account);
				break;
			case DELETE_SECONDARY_ACCOUNT:
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
	private static void editBankCards(Integer customerId) {
		Integer option;
		DebitCard debitCard = null;
		CreditCard creditCard = null;
//		Nao deveria ser necessario perguntar se está a usar credito ou debito
		// Deverá ser necessario a lista Card e identificar de alguma forma o credito e
		// o debito
		System.out.println("Please tell what card are you goind to use: debit/credit");
		String cardOption = scanner.next().toLowerCase();

		if (cardOption.equals("debit")) {
			debitCard = cardService.findCustomerDebitCard(customerId);
			if (debitCard == null) {
				return;
			}
		}
		if (cardOption.equals("credit")) {
			creditCard = cardService.findCustomerCreditCard(customerId);
			if (creditCard == null) {
				return;
			}
		}

		do {

			displayBankCardsMenu();

			option = scanner.nextInt();

			switch (option) {
			case EDIT_DEBIT_CARDS:
				editBankDebitCards(debitCard);
				// Add Debit card
				break;
			case EDIT_CREDIT_CARDS:
				editBankCreditCards(creditCard);
				// Remove Debit card
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

	private static void editBankDebitCards(DebitCard debitCard) {
		Integer option;

		do {

			displayBankDebitCards();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_DEBIT_CARD:
				cardService.createDebitCard(debitCard);
				// Add Debit card
				break;
			case DELETE_DEBIT_CARD:
				cardService.deleteDebitCard(debitCard);
				// Remove Debit card
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in editBankDebitCards");
				break;
			}
		} while (option != 0);
	}

	private static void editBankCreditCards(CreditCard creditCard) {
		Integer option;

		do {

			displayBankCreditCards();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_CREDIT_CARD:
				cardService.createCreditCard(creditCard);
				// Add Debit card
				break;
			case DELETE_CREDIT_CARD:
				cardService.deleteCreditCard(creditCard);
				// Remove Debit card
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in editBankCreditCards");
				break;
			}
		} while (option != 0);
	}

	/******************************************************************************
	 * Money movement
	 * 
	 * 
	 ******************************************************************************/

	private static void moneyManagement(Integer customerId) {
		Integer option;
		Account account;

		account = accountService.findCustomerAccount(customerId);

		if (account == null) {
			return;
		}

		do {
			displayMoneyManagementMenu();
			option = scanner.nextInt();
			switch (option) {
			case DEPOSIT_MONEY_ON_HOLDER_ACCOUNT:
				accountService.depositMoneyOnHolderAccount(account.getCustomerId());
				break;
			case DEPOSIT_MONEY_ON_SECONDARY_ACCOUNT:
				accountService.depositMoneyOnSecondaryAccount(account.getCustomerId());
				break;
			case WITHDRAW_MONEY_ON_HOLDER_ACCOUNT:
				accountService.withdrawMoneyOnHolderAccount(account.getCustomerId());
				break;
			case WITHDRAW_MONEY_ON_SECONDARY_ACCOUNT:
				accountService.withdrawMoneyOnSecondaryAccount(account.getCustomerId());
				break;
			case TRANSFER_MONEY:
				accountService.transferMoney(account.getCustomerId());
				break;
			case CHECK_ACCOUNT_HISTORY:
				accountService.showAccountHistoryMovement(account.getCustomerId());
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

		customer = populateCustomer();
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

		account = populateAccount();
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
		DebitCard debitCard = new DebitCard();
		CreditCard creditCard = new CreditCard();

		debitCard = populateDebitCard();
		creditCard = populateCreditCard();
		System.out.println(CUSTOMER_CREATED);
		cardService.save(debitCard, creditCard);

		cardService.showDebitCardsDetails();
		cardService.showCreditCardsDetails();
	}

	/******************************************************************************
	 * Show all customer
	 * 
	 * @return
	 ******************************************************************************/
	private static void showAllCustomersDetails() {
		customerService.showCustomersDetails();
		accountService.showAccountsDetails();
		cardService.showDebitCardsDetails();
		cardService.showCreditCardsDetails();
	}

	/******************************************************************************
	 * Populate the Customers
	 * 
	 * @return TODO Por na camada console!!!!!!!!!!!!!!!!!!!!!!!
	 ******************************************************************************/
	public static Customer populateCustomer() {
		System.out.println("Creating new client");
		Customer newCustomer = new Customer();

		System.out.println("Please set Name");
		newCustomer.setName(scanner.next());

		do {
			System.out.println("Please set TaxId");
			String taxId = scanner.next();
			if (!customerService.taxIDalreadyExists(taxId))
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
	 * Populate the Cards
	 * 
	 * 
	 ******************************************************************************/
	public static DebitCard populateDebitCard() {
		DebitCard newDebitCard = new DebitCard();
		CreditCard newCreditCard = new CreditCard();
		String cardNumber;
		String pinCard;

		System.out.println("Do you which to have a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			System.out.println("Please indicate the debit card number");
			cardNumber = scanner.next();
			newDebitCard.setDebitCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the debit card number");
			pinCard = scanner.next();
			newDebitCard.setDebitCardPin(pinCard);
		}

		return newDebitCard;
	}

	public static CreditCard populateCreditCard() {
		CreditCard newCreditCard = new CreditCard();
		String cardNumber;
		String pinCard;

		System.out.println("Do you which to have a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			System.out.println("Please indicate the credit card number");
			cardNumber = scanner.next();
			newCreditCard.setCreditCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the credit card number");
			pinCard = scanner.next();
			newCreditCard.setCreditCardPin(pinCard);
		}

		return newCreditCard;
	}

	/******************************************************************************
	 * Populate the Accounts
	 * 
	 * @return
	 ******************************************************************************/

	public static Account populateAccount() {
		System.out.println("Creating new account");
		Account newAccount = new Account();

		System.out.println("Please set the number of the account (5 digits)");
		newAccount.setAccountHolderNumber(scanner.next());

		System.out.println("Please set the password for your holder account");
		newAccount.setPasswordAccount(scanner.next());

		do {
			System.out.println("Please set the customer balance on its main account");
			Double moneyDeposit = scanner.nextDouble();
			if (moneyDeposit > 50) {
				newAccount.setAccountHolderBalance(moneyDeposit);

			} else {
				System.err.println("In order to create an account its necessary to deposit >=50€\n");
			}
		} while (newAccount.getAccountHolderBalance() == null);

		System.out.println("Please indicate how many secondary accounts do you want to have");
		Integer count = scanner.nextInt();
		String secondaryAccount;
		for (int i = 0; i < count; i++) {
			System.out.printf("\nPlease indicate the %dº Account Number of the Secondary accounts to be associated ",
					i + 1);
			secondaryAccount = scanner.next();
			newAccount.getSecondaryAccountNumber()[i] = secondaryAccount;
		}

		return newAccount;
	}

	/******************************************************************************
	 * Display menus
	 ******************************************************************************/
	private static void displayInitiation() {

		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Create new customer");
		System.out.println("2 - Choose the program to execute");
		System.out.println("3 - Show all customers");
		System.out.println("###########################################################################");

	}

	private static void displayChooseProgram() {

		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Use program online");
		System.out.println("2 - Use ATM");
		System.out.println("###########################################################################");

	}

	private static void displayATM() {

		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Manage Money");
		System.out.println("2 - Show account history");
		System.out.println("###########################################################################");
	}

	private static void displayOnline() {

		System.out.println("############################ " + TITLE + " ############################");
		System.out.println("\t\tPlease choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Edit Customer Details");
		System.out.println("2 - Show Customer");
		System.out.println("3 - Change Password Account");
		System.out.println("4 - Show Account Details");
		System.out.println("5 - Transfer money");
		System.out.println("6 - Check movement history online");
		System.out.println("7 - Edit secondary accounts");
		System.out.println("8 - Edit Bank cards");
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
		System.out.println("1 - Edit Debit Card");
		System.out.println("2 - Edit Credit Card");
		System.out.println("###########################################################################");
	}

	private static void displayBankDebitCards() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Debit Card");
		System.out.println("2 - Delete Debit Card");
		System.out.println("###########################################################################");
	}

	private static void displayBankCreditCards() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Credit Card");
		System.out.println("2 - Delete Credit Card");
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

	private static void displayEditCustomerDetailsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("What do you which to edit?");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Name of the customer");
		System.out.println("2 - Email of the customer");
		System.out.println("###########################################################################");
	}

//	private static void example() {
//		ICustomerRepository customerRepository = new InMemCustomerRepository();
//		NewCustomerService service = new NewCustomerService(customerRepository);
//		
//		service.
//	}
}
