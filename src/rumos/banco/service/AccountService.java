package rumos.banco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Customer;

public class AccountService {
	private static final int EXIT = 0;
             
             
	private static final int DEPOSIT_MONEY_ON_HOLDER_ACCOUNT = 1;
	private static final int DEPOSIT_MONEY_ON_SECUNDARY_ACCOUNT = 2;
	private static final int WITHDRAW_MONEY_ON_HOLDER_ACCOUNT = 3;
	private static final int WITHDRAW_MONEY_ON_SECUNDARY_ACCOUNT = 4;
	private static final int TRANSFER_MONEY = 5;
	private static final int CHECK_ACCOUNT_HISTORY = 6;
             

	private static final String TITLE = "Rumos Digital Bank";
	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";
	private static final String NO_MONEY_TO_REMOVE = "You are removing an amount bigger than what you own";
	
	
	private ArrayList<Account> accounts = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	
	
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
				addSecondaryAccount(customer);
				break;
			case 2:
				deleteSecondaryAccount(customer);
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
	
	
	private static void addSecondaryAccount(Account account) {
		Integer index = checkSecondaryAccounts(account);
		String secondAccount;

		if (index < 4) {
			System.out.println("Please indicate what account do you wish to have as Second account?");
			secondAccount = scanner.next();
			for (String checkIfTheSameAccount : account.getSecundaryAccountNumber()) {
				if (checkIfTheSameAccount.equals(secondAccount)) {
					System.err.println("The account that you are requesting to be secondary it is your Holder acocunt");
					return;
				}
			}
			if (checkIfAccountHolderExists(secondAccount)) {
				account.getSecundaryAccountNumber()[index] = secondAccount;
				return;
			}
			return;
		}
	}
	
	private static boolean checkIfAccountHolderExists(String secondaryAccount) {
		for (Account account : accounts) {
			if (account == null)
				break;
			if (account.getAccountHolderNumber().equals(secondaryAccount)) {
				System.out.println(
						"The account holder number that you which to associate as your secondary account exists ");
				System.out.println("The details of the costumer are: " + account.toString());
				return true;
			}
		}
		System.err.println("There is no Holder Account customer with that account");
		return false;
	}
	
	private static int checkSecondaryAccounts(Account account) {
		Integer index = 4;
		for (int i = 0; i < account.getSecundaryAccountNumber().length; i++) {
			if (account.getSecundaryAccountNumber()[i].isBlank()) {
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
	
	private static void deleteSecondaryAccount(Account account) {
		String secondaryAccount;

		if (!checkIfItHasSecondaryAccounts(account))
			return;

		System.out.println("Please indicate which secondary account you wish to delete");
		secondaryAccount = scanner.next();

		for (int i = 0; i < account.getSecundaryAccountNumber().length; i++) {
			if (account.getSecundaryAccountNumber()[i].equals(secondaryAccount)) {
				account.getSecundaryAccountNumber()[i] = "";
				System.out.println("Account deleted");
				System.out.print("The remaining secondary accounts are: "
						+ Arrays.toString(account.getSecundaryAccountNumber()));
				return;
			}
		}
		System.out.println("The account that you have requested to delete does not belong to you");

	}
	
	private static boolean checkIfItHasSecondaryAccounts(Account account) {

		for (String secondaryAccounts : account.getSecundaryAccountNumber()) {
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

	private static void depositMoneyOnHolderAccount(Account account) {

		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		account.setHolderAccountBalance(account.getHolderAccountBalance() + Math.abs(amount));
		addTAccountHistoryMovement(account, amount.toString());
		return;
	}

	private static void depositMoneyOnSecundaryAccount(Account account) {
		String secondaryAccount = checkSecondaryAccount(account);
		String decision;

		if (secondaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECUNDARY NUMBER CORRECTO OU QUERER SAIR

		for (Account getAccount : accounts) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccount)) {
				depositMoneyOnHolderAccount(getAccount);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;

	}

	private static void withdrawMoneyOnHolderAccount(Account account) {

		System.out.println("Please indicate the amount of money you wish to take");
		Double amount = scanner.nextDouble();

		if ((account.getHolderAccountBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}
		account.setHolderAccountBalance(account.getHolderAccountBalance() - Math.abs(amount));
		addTAccountHistoryMovement(account, "-" + amount.toString());
		System.out.printf("\nAt the moment you have %f on your account\n", account.getHolderAccountBalance());
		return;
	}

	private static void withdrawMoneyOnSecondaryAccount(Account account) {
		String secondaryAccount = checkSecondaryAccount(account);
		String decision;

		if (secondaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECUNDARY NUMBER CORRECTO OU QUERER SAIR

		for (Account getAccount : accounts) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccount)) {
				withdrawMoneyOnHolderAccount(getAccount);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;
	}

	private static String checkSecondaryAccount(Account account) {
		System.out.println("From which of your secundary account do want to perform action? ");
		System.out.printf("\nThe accounts that you have associated are: %s",
				Arrays.toString(account.getSecundaryAccountNumber()));
		String secundaryAccount;

		secundaryAccount = scanner.next();
		for (String otherAccounts : account.getSecundaryAccountNumber()) {
			if (otherAccounts.equals(secundaryAccount)) {
				System.out.println("The choosen account is: " + otherAccounts);
				return otherAccounts;
			}
		}
		System.err.println("The secondary account does not correspond to the ones that you own");
		return null;
	}

	private static void transferMoney(Account account) {

		Double amount;
		String accountToTransfer;

		System.out.println("Please indicate the amount of money you wish to transfer");
		amount = scanner.nextDouble();

		if (account.getHolderAccountBalance() - Math.abs(amount) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}

		System.out.println("Please indicate to which account do you want to transfer?");
		accountToTransfer = scanner.next();
		for (Account customerToTransfer : accounts) {
			if (customerToTransfer == null)
				break;
			if (customerToTransfer.getAccountHolderNumber().equals(accountToTransfer)) {
				System.out.printf("\nThe account belongs to Name: %s, TaxId: %s and Account Number: %s\n",
						customerToTransfer.getName(), customerToTransfer.getTaxId(),
						customerToTransfer.getAccountHolderNumber());
				customerToTransfer.setHolderAccountBalance(customerToTransfer.getHolderAccountBalance() + amount);
				account.setHolderAccountBalance(account.getHolderAccountBalance() - Math.abs(amount));
				addTAccountHistoryMovement(account, "-" + amount.toString());
				System.out.println("Presenting both accounts values");
				System.out.println("From: " + account.toString());
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

	private static void showAccountHistoryMovement(Account account) {
		for (String transactionHistory : account.getTransactionHistory()) {
			System.out.println("The transaction history: " + transactionHistory);
		}
		System.out.println("The total amount of: " + account.getHolderAccountBalance());
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

		for (Account account : accounts) {
			if (account == null)
				break;
			if (account.getName().equals(name) && account.getPassword().equals(password)) {
				return account;
			}
		}
		System.err.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}
	
	
	

	private static void addTAccountHistoryMovement(Account account, String amount) {
		account.getTransactionHistory().add(amount);
	}
	
	private static void displaySecondaryAccountsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Secondary Account");
		System.out.println("2 - Delete Secondary Account");
		System.out.println("###########################################################################");
	}
	
	private static void displayMoneyManagementMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Deposit money on your Holder Account");
		System.out.println("2 - Deposit money on your Secundary Account");
		System.out.println("3 - Withdraw money from your Holder Account");
		System.out.println("4 - Withdraw money from your Secondary Account");
		System.out.println("5 - Transfer money to another account");
		System.out.println("6 - Show history account");
		System.out.println("###########################################################################");
	}
	
}
