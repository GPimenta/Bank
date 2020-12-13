package rumos.banco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Customer;

public class AccountService {
	private final int EXIT = 0;
	private final int CREATE_NEW_CUSTOMER = 1;
	private final int SHOW_CUSTOMER = 2;
	private final int EDIT_CUSTOMER_ACCOUNTS = 3;
	private final int TO_DECIDE_2 = 4;
	private final int EDIT_CUSTOMER_PERSONAL_DETAILS = 5;
	private final int DELETE_CUSTOMER = 6;
	private final int MANAGE_MONEY = 7;
	private final int EDIT_BANK_CARDS = 8;

	private final int SHOW_CUSTOMER_BY_NAME = 1;
	private final int SHOW_CUSTOMER_BY_TAXID = 2;
	private final int SHOW_ALL_CUSTOMER = 3;

	private final int EDIT_CUSTOMER_BY_NAME_AND_PASSWORD = 1;
	private final int EDIT_CUSTOMER_BY_TAXID = 2;
	private final int EDIT_CUSTOMER_BY_ID = 3;

	private final int CHANGE_NAME = 1;
	private final int CHANGE_PASSWORD = 2;
	private final int CHANGE_EMAIL = 3;

	private final int CREATE_DEBIT_CARD = 1;
	private final int DELETE_DEBIT_CARD = 2;
	private final int CREATE_CREDIT_CARD = 3;
	private final int DELETE_CREDIT_CARD = 4;

	private final int DEPOSIT_MONEY_ON_HOLDER_ACCOUNT = 1;
	private final int DEPOSIT_MONEY_ON_SECUNDARY_ACCOUNT = 2;
	private final int WITHDRAW_MONEY_ON_HOLDER_ACCOUNT = 3;
	private final int WITHDRAW_MONEY_ON_SECUNDARY_ACCOUNT = 4;
	private final int TRANSFER_MONEY = 5;
	private final int CHECK_ACCOUNT_HISTORY = 6;

	private final String MOTD = "Welcome to Rumos Digital Bank";
	private final String TITLE = "Rumos Digital Bank";
	private final String GOODBYE = "Thanks for using Rumos Digital Bank";
	private final String CUSTOMER_CREATED = "Customer created!";
	private final String CUSTOMER_DELETED = "Customer deleted!";
	private final String CUSTOMER_NOT_FOUND = "Customer not found!";
	private final String DATABASE_IS_FULL = "Database is full!";
	private final String DATABASE_IS_EMPTY = "Database is empty!";
	private final String TAXID_ALREADY_EXISTS = "Customer Tax Id already exists!";
	private final String INVALID_OPTION = "Invalid Option!";
	private final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
	private final String INVALID_TAXID = "Incorrect TaxID";
	private final String INVALID_ID = "Incorrect ID";
	private final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private final String NO_ADD_CREDIT_CARD = "You already have a Credit Card";
	private final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private final String NO_TAKE_CREDIT_CARD = "You do not have a Credit Card";
	private final String PREVIOUS_MENU = "Returning to previous Menu";
	private final String NO_MONEY_TO_REMOVE = "You are removing an amount bigger than what you own";
	
	
	ArrayList<Account> Accounts = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	
	
	private static void editSecondaryAccounts() {
		Customer customer = checkNameAndPassword();
		Integer choose;

		if (customer == null) {
			return;
		}

		do {
			displaySecondaryAccountsMenu();

			choose = scanner.nextInt();

			switch (choose) {
			case 1:
				addSecundaryAccount(customer);
				break;
			case 2:
				deleteSecundaryAccount(customer);
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
	
	
	private static void addSecondaryAccount(Customer customer) {
		Integer index = checkSecondaryAccounts(customer);
		String secondAccount;

		if (index < 4) {
			System.out.println("Please indicate what account do you wish to have as Second account?");
			secondAccount = scanner.next();
			for (String checkIfTheSameAccount : customer.getSecundaryAccountNumber()) {
				if (checkIfTheSameAccount.equals(secondAccount)) {
					System.err.println("The account that you are requesting to be secondary it is your Holder acocunt");
					return;
				}
			}
			if (checkIfAccountHolderExists(secondAccount)) {
				customer.getSecundaryAccountNumber()[index] = secondAccount;
				return;
			}
			return;
		}
	}
	
	private static boolean checkIfAccountHolderExists(String secondaryAccount) {
		for (Customer costumer : customers) {
			if (costumer == null)
				break;
			if (costumer.getAccountHolderNumber().equals(secondaryAccount)) {
				System.out.println(
						"The account holder number that you which to associate as your secondary account exists ");
				System.out.println("The details of the costumer are: " + costumer.toString());
				return true;
			}
		}
		System.err.println("There is no Holder Account customer with that account");
		return false;
	}
	
	private static int checkSecondaryAccounts(Customer customer) {
		Integer index = 4;
		for (int i = 0; i < customer.getSecundaryAccountNumber().length; i++) {
			if (customer.getSecundaryAccountNumber()[i].isBlank()) {
				System.out.println("There is possability to add an secondary account");
				index = i;
				return index;
			}
		}
		System.out.println("You can not have more secondary accounts"); // COMO TENHO EM VECTOR TENHO Q TRAZER COMIGO UM
																		// INDEX. TALVEZ PARA SIMPLIFICAR DEVA
																		// TRANSFORMAR EM LISTA
		return index;
	}
	
	private static void deleteSecondaryAccount(Customer customer) {
		String secondaryAccount;

		if (!checkIfItHasSecondaryAccounts(customer))
			return;

		System.out.println("Please indicate which secondary account you wish to delete");
		secondaryAccount = scanner.next();

		for (int i = 0; i < customer.getSecundaryAccountNumber().length; i++) {
			if (customer.getSecundaryAccountNumber()[i].equals(secondaryAccount)) {
				customer.getSecundaryAccountNumber()[i] = "";
				System.out.println("Account deleted");
				System.out.print("The remaining secondary accounts are: "
						+ Arrays.toString(customer.getSecundaryAccountNumber()));
				return;
			}
		}
		System.out.println("The account that you have requested to delete does not belong to you");

	}
	
	private static boolean checkIfItHasSecondaryAccounts(Customer customer) {

		for (String secondaryAccounts : customer.getSecundaryAccountNumber()) {
			if (secondaryAccounts.isBlank())
				return true;
		}
		System.err.println("You do not have Secondary accounts to delete.");
		return false;
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
			displayMoneyManagementMenu();
			option = scanner.nextInt();
			switch (option) {
			case DEPOSIT_MONEY_ON_HOLDER_ACCOUNT:
				depositMoneyOnHolderAccount(customer);
				break;
			case DEPOSIT_MONEY_ON_SECUNDARY_ACCOUNT:
				depositMoneyOnSecundaryAccount(customer);
				break;
			case WITHDRAW_MONEY_ON_HOLDER_ACCOUNT:
				withdrawMoneyOnHolderAccount(customer);
				break;
			case WITHDRAW_MONEY_ON_SECUNDARY_ACCOUNT:
				withdrawMoneyOnSecondaryAccount(customer);
				break;
			case TRANSFER_MONEY:
				transferMoney(customer);
				break;
			case CHECK_ACCOUNT_HISTORY:
				showAccountHistoryMovement(customer);
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

	private static void depositMoneyOnHolderAccount(Customer customer) {

		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		customer.setHolderAccountBalance(customer.getHolderAccountBalance() + Math.abs(amount));
		addTAccountHistoryMovement(customer, amount.toString());
		return;
	}

	private static void depositMoneyOnSecundaryAccount(Customer customer) {
		String secundaryAccount = checkSecondaryAccount(customer);
		String decision;

		if (secundaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECUNDARY NUMBER CORRECTO OU QUERER SAIR

		for (Customer getCustomer : customers) {
			if (getCustomer.getAccountHolderNumber().equals(secundaryAccount)) {
				depositMoneyOnHolderAccount(getCustomer);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;

	}

	private static void withdrawMoneyOnHolderAccount(Customer customer) {

		System.out.println("Please indicate the amount of money you wish to take");
		Double amount = scanner.nextDouble();

		if ((customer.getHolderAccountBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}
		customer.setHolderAccountBalance(customer.getHolderAccountBalance() - Math.abs(amount));
		addTAccountHistoryMovement(customer, "-" + amount.toString());
		System.out.printf("\nAt the moment you have %f on your account\n", customer.getHolderAccountBalance());
		return;
	}

	private static void withdrawMoneyOnSecondaryAccount(Customer customer) {
		String secundaryAccount = checkSecondaryAccount(customer);
		String decision;

		if (secundaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECUNDARY NUMBER CORRECTO OU QUERER SAIR

		for (Customer getCustomer : customers) {
			if (getCustomer.getAccountHolderNumber().equals(secundaryAccount)) {
				withdrawMoneyOnHolderAccount(getCustomer);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;
	}

	private static String checkSecondaryAccount(Customer customer) {
		System.out.println("From which of your secundary account do want to perform action? ");
		System.out.printf("\nThe accounts that you have associated are: %s",
				Arrays.toString(customer.getSecundaryAccountNumber()));
		String secundaryAccount;

		secundaryAccount = scanner.next();
		for (String otherAccounts : customer.getSecundaryAccountNumber()) {
			if (otherAccounts.equals(secundaryAccount)) {
				System.out.println("The choosen account is: " + otherAccounts);
				return otherAccounts;
			}
		}
		System.err.println("The secondary account does not correspond to the ones that you own");
		return null;
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
			if (customerToTransfer == null)
				break;
			if (customerToTransfer.getAccountHolderNumber().equals(accountToTransfer)) {
				System.out.printf("\nThe account belongs to Name: %s, TaxId: %s and Account Number: %s\n",
						customerToTransfer.getName(), customerToTransfer.getTaxId(),
						customerToTransfer.getAccountHolderNumber());
				customerToTransfer.setHolderAccountBalance(customerToTransfer.getHolderAccountBalance() + amount);
				customer.setHolderAccountBalance(customer.getHolderAccountBalance() - Math.abs(amount));
				addTAccountHistoryMovement(customer, "-" + amount.toString());
				System.out.println("Presenting both accounts values");
				System.out.println("From: " + customer.toString());
				System.out.println("To: " + customerToTransfer.toString());
				return;
			}

		}
		System.err.println("There is no account with that number.");
		return;
	}
	/**
	 * Account History movement
	 *
	 */

	private static void showAccountHistoryMovement(Customer customer) {
		for (String transactionHistory : customer.getTransactionHistory()) {
			System.out.println("The transaction history: " + transactionHistory);
		}
		System.out.println("The total amount of: " + customer.getHolderAccountBalance());
	}

	private static void addTAccountHistoryMovement(Customer customer, String amount) {
		customer.getTransactionHistory().add(amount);
	}

	
}
