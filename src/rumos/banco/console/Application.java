package rumos.banco.console;

import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.CreditCard;
import rumos.banco.model.Customer;
import rumos.banco.model.DebitCard;
import rumos.banco.repository.ICreditCardRepository;
import rumos.banco.repository.ICustomerRepository;
import rumos.banco.repository.IDebitCardRepository;
import rumos.banco.repository.InMemCreditCardRepositoryImpl;
import rumos.banco.repository.InMemCustomerRepositoryImpl;
import rumos.banco.repository.InMemDebitCardRepositoryImpl;
import rumos.banco.service.AccountService;
import rumos.banco.service.CreditCardService;
import rumos.banco.service.CustomerService;
import rumos.banco.service.DebitCardService;

public class Application {
	private static final int EXIT = 0;

	private static final int CREATE_NEW_CUSTOMER = 1;
	private static final int CHOOSE_THE_PROGRAM = 2;
	private static final int SHOW_ALL_CUSTOMERS = 3;
	private static final int DELETE_CUSTOMER = 4;

	private static final int GO_TO_ONLINE = 1;
	private static final int GO_TO_ATM_DEBIT_CARD = 2;
	private static final int GO_TO_ATM_CREDIT_CARD = 3;

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

	private static final int CREATE_CREDIT_CARD = 1;
	private static final int DELETE_CREDIT_CARD = 2;

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
	private static final String ACCOUNT_CREATED = "Account created!";
	private static final String DEBIT_CARD_CREATED = "Debit created!";
	private static final String CREDIT_CARD_CREATED = "Customer created!";

	private static final String CUSTOMER_NOT_CREATED = "Customer not created!";
	private static final String ACCOUNT_NOT_CREATED = "Account not created!";
	private static final String DEBIT_CARD_NOT_CREATED = "Debit Card not created!";
	private static final String CREDIT_CARD_NOT_CREATED = "Credit Card not created!";

	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";

	private static Scanner scanner = new Scanner(System.in);

	private static ICustomerRepository customerRepository = new InMemCustomerRepositoryImpl();
	private static IDebitCardRepository debitRepository = new InMemDebitCardRepositoryImpl();
	private static ICreditCardRepository creditRepository = new InMemCreditCardRepositoryImpl();

	private static CustomerService customerService = new CustomerService(customerRepository);
	private static DebitCardService debitCardService = new DebitCardService(debitRepository);
	private static CreditCardService creditCardService = new CreditCardService(creditRepository);
	private static AccountService accountService = new AccountService();

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
				System.out.println("Please indicate the name of the client");
				String name = scanner.next();
				System.out.println("Please indicate the taxId");
				String taxId = scanner.next();
				Integer customerId = customerService.deleteCustomerDetails(name, taxId);
				accountService.deleteAccount(customerId);
				debitCardService.deleteDebitCardTesting(customerId);
				creditCardService.deleteCreditCard(customerId);

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
			case GO_TO_ATM_DEBIT_CARD:
				chooseATM_DebitCard();
				// Use the ATM
				break;
			case GO_TO_ATM_CREDIT_CARD:
				chooseATM_CreditCard();
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

	private static void chooseATM_DebitCard() {
		Integer option;
		DebitCard debitCard;
		String cardNumber, cardPin;

		System.out.println("Please indicate your Card Number");
		cardNumber = scanner.next();
		System.out.println("Please indicate your Card Pin");
		cardPin = scanner.next();

		debitCard = debitCardService.checkCardNumberAndPassword(cardNumber, cardPin);

		if (debitCard == null)
			return;

		do {

			displayATM();

			option = scanner.nextInt();
			switch (option) {

			case MANAGE_MONEY:
				moneyManagement(debitCard.getCustomerId());
				// Take, put and transfer money
				break;
			case CHECK_ACCOUNT_HISTORY_THROUGH_ATM:
				accountService.showAccountHistoryMovement(debitCard.getCustomerId());
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

	private static void chooseATM_CreditCard() {
		Integer option;
		CreditCard creditCard;
		String cardNumber, cardPin;

		System.out.println("Please indicate your Card Number");
		cardNumber = scanner.next();
		System.out.println("Please indicate your Card Pin");
		cardPin = scanner.next();

		creditCard = creditCardService.checkCardNumberAndPassword(cardNumber, cardPin);

		if (creditCard == null)
			return;

		do {

			displayATM();

			option = scanner.nextInt();
			switch (option) {

			case MANAGE_MONEY:
				moneyManagement(creditCard.getCustomerId());
				// Take, put and transfer money
				break;
			case CHECK_ACCOUNT_HISTORY_THROUGH_ATM:
				accountService.showAccountHistoryMovement(creditCard.getCustomerId());
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

		customer = customerService.getCustomerById(customerId);

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
	 * 
	 * 
	 ******************************************************************************/
	private static void editBankCards(Integer customerId) {
		Integer option;
		DebitCard debitCard = null;
		CreditCard creditCard = null;

		do {

			displayBankCardsMenu();

			option = scanner.nextInt();

			switch (option) {
			case EDIT_DEBIT_CARDS:
				editBankDebitCards(customerId);
				// Add Debit card
				break;
			case EDIT_CREDIT_CARDS:
				editBankCreditCards(customerId);
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

	private static void editBankDebitCards(Integer customerId) {
		Integer option;
		String cardNumber, cardPin;
		DebitCard debitCard = null;

		do {
			debitCard = debitCardService.findCustomerDebitCard(customerId);
			displayBankDebitCards();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_DEBIT_CARD:
				System.out.println("Please indicate the Card Number");
				cardNumber = scanner.next();
				System.out.println("Please indicate the Card Pin");
				cardPin = scanner.next();
				debitCardService.createDebitCard(debitCard, cardNumber, cardPin, customerId);
				// Add Debit card
				break;
			case DELETE_DEBIT_CARD:
				debitCardService.deleteDebitCard(debitCard);
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

	private static void editBankCreditCards(Integer customerId) {
		Integer option;
		String cardNumber, cardPin;
		CreditCard creditCard = null;

		do {
			creditCard = creditCardService.findCustomerCreditCard(customerId);
			displayBankCreditCards();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_CREDIT_CARD:
				System.out.println("Please indicate the Card Number");
				cardNumber = scanner.next();
				System.out.println("Please indicate the Card Pin");
				cardPin = scanner.next();
				creditCardService.createCreditCard(creditCard, cardNumber, cardPin, customerId);
				// Add Debit card
				break;
			case DELETE_CREDIT_CARD:
				creditCardService.deleteCreditCard(creditCard);
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
//		CreateNewCard(); TODO Changed here
		CreateNewDebitCard();
		CreateNewCreditCard();

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
		customerService.create(customer);

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
		System.out.println(ACCOUNT_CREATED);
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
		if (debitCard == null) {
			// TODO Changed here
			System.out.println(DEBIT_CARD_NOT_CREATED);
			return;
		}
//		if(creditCard.getCreditCardNumber().equals(null)) {
//			System.out.println(CREDIT_CARD_NOT_CREATED);
//		}
		creditCard = populateCreditCard();
		System.out.println(CUSTOMER_CREATED);
		debitCardService.create(debitCard);
		System.out.println(CUSTOMER_CREATED);
		creditCardService.create(creditCard);
		System.out.println(CUSTOMER_CREATED);

		debitCardService.showDebitCardsDetails();
		creditCardService.showCreditCardsDetails();
	}

	private static void CreateNewDebitCard() {
		DebitCard debitCard = new DebitCard();

		debitCard = populateDebitCard();
		if (debitCard == null) {
			// TODO Changed here
			System.out.println(DEBIT_CARD_NOT_CREATED);
			return;
		}
		debitCardService.create(debitCard);
		System.out.println(DEBIT_CARD_CREATED);

		debitCardService.showDebitCardsDetails();
	}

	private static void CreateNewCreditCard() {
		CreditCard creditCard = new CreditCard();

		creditCard = populateCreditCard();
		if (creditCard == null) {
			// TODO Changed here
			System.out.println(CREDIT_CARD_NOT_CREATED);
			return;
		}
//		if(creditCard.getCreditCardNumber().equals(null)) {
//			System.out.println(CREDIT_CARD_NOT_CREATED);
//		}

		creditCardService.create(creditCard);
		System.out.println(CREDIT_CARD_CREATED);

		creditCardService.showCreditCardsDetails();
	}

	/******************************************************************************
	 * Show all customer
	 * 
	 * @return
	 ******************************************************************************/
	private static void showAllCustomersDetails() {
		customerService.showCustomersDetails();
		accountService.showAccountsDetails();
		debitCardService.showDebitCardsDetails();
		creditCardService.showCreditCardsDetails();
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
		String cardNumber;
		String pinCard;

		System.out.println("Do you wish to have a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			System.out.println("Please indicate the debit card number");
			cardNumber = scanner.next();
			newDebitCard.setDebitCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the debit card number");
			pinCard = scanner.next();
			newDebitCard.setDebitCardPin(pinCard);
			return newDebitCard;
		} else {
			// TODO Changed here
			return null;
		}

	}

	public static CreditCard populateCreditCard() {
		CreditCard newCreditCard = new CreditCard();
		String cardNumber;
		String pinCard;

		System.out.println("Do you wish to have a Credit Card? y/n");
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
		
		System.out.println("Do you wish to have a secondary account: y/n");
		String choice = scanner.next();
		if(choice.equals("y")) {
			
		}
		
		//TODO
		

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
		System.out.println("2 - Use ATM with DebitCard");
		System.out.println("3 - Use ATM with CreditCard");
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
