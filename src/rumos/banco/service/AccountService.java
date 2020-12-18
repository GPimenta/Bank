package rumos.banco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Customer;
import rumos.banco.model.Customer_Old;

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

	private static ArrayList<Account> accounts = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer id = 0;
	
	/******************************************************************************
	 * Add Account
	 * 
	 * 
	 ******************************************************************************/

	public Account save(Account account) {
		id++;
		account.setCustomerId(id);
		accounts.add(account);

		return account;
	}

	/******************************************************************************
	 * Check secondaryAccounts
	 * 
	 * 
	 ******************************************************************************/

	public void editSecondaryAccounts() {
		Account account = checkAccountNameAndPassword();
		Integer choose;

		if (account == null) {
			return;
		}

		do {
			displaySecondaryAccountsMenu();

			choose = scanner.nextInt();

			switch (choose) {
			case 1:
				addSecondaryAccount(account);
				break;
			case 2:
				deleteSecondaryAccount(account);
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

	public void addSecondaryAccount(Account account) {
		Integer index = checkSecondaryAccounts(account);
		String secondAccount;

		if (index < 4) {
			System.out.println("Please indicate what account do you wish to have as Second account?");
			secondAccount = scanner.next();
			for (String checkIfTheSameAccount : account.getSecondaryAccountNumber()) {
				if (checkIfTheSameAccount.equals(secondAccount)) {
					System.err.println("The account that you are requesting to be secondary it is already your secondary account");
					return;
				}
			}
			if(account.getAccountHolderNumber().equals(secondAccount)) {
				System.out.println("The action cannot be perform, you can not make your holder account as secondary");
				return;
			}
			if (checkIfAccountHolderExists(secondAccount)) {
				account.getSecondaryAccountNumber()[index] = secondAccount;
				return;
			}
			return;
		}
	}

	public boolean checkIfAccountHolderExists(String secondaryAccount) {
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

	public int checkSecondaryAccounts(Account account) {
		Integer index = 4;
		for (int i = 0; i < account.getSecondaryAccountNumber().length; i++) {
			if (account.getSecondaryAccountNumber()[i].isBlank()) {
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

	public void deleteSecondaryAccount(Account account) {
		String secondaryAccount;

		if (!checkIfItHasSecondaryAccounts(account))
			return;

		System.out.println("Please indicate which secondary account you wish to delete");
		secondaryAccount = scanner.next();

		for (int i = 0; i < account.getSecondaryAccountNumber().length; i++) {
			if (account.getSecondaryAccountNumber()[i].equals(secondaryAccount)) {
				account.getSecondaryAccountNumber()[i] = "";
				System.out.println("Account deleted");
				System.out.println("The remaining secondary accounts are: "
						+ Arrays.toString(account.getSecondaryAccountNumber()));
				return;
			}
		}
		System.out.println("The account that you have requested to delete does not belong to you");

	}

	public boolean checkIfItHasSecondaryAccounts(Account account) {

		for (String secondaryAccounts : account.getSecondaryAccountNumber()) {
			if (secondaryAccounts.isBlank())
				return true;
		}
		System.err.println("You do not have Secondary accounts to delete.");
		return false;
	}

	/******************************************************************************
	 * Money movement
	 * 
	 * 
	 ******************************************************************************/

	public void moneyManagement() {
		Integer option;
		Account account;

		account = checkAccountNameAndPassword();

		if (account == null) {
			return;
		}

		do {
			displayMoneyManagementMenu();
			option = scanner.nextInt();
			switch (option) {
			case DEPOSIT_MONEY_ON_HOLDER_ACCOUNT:
				depositMoneyOnHolderAccount(account);
				break;
			case DEPOSIT_MONEY_ON_SECUNDARY_ACCOUNT:
				depositMoneyOnSecondaryAccount(account);
				break;
			case WITHDRAW_MONEY_ON_HOLDER_ACCOUNT:
				withdrawMoneyOnHolderAccount(account);
				break;
			case WITHDRAW_MONEY_ON_SECUNDARY_ACCOUNT:
				withdrawMoneyOnSecondaryAccount(account);
				break;
			case TRANSFER_MONEY:
				transferMoney(account);
				break;
			case CHECK_ACCOUNT_HISTORY:
				showAccountHistoryMovement(account);
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
	 * Deposit money on accounts
	 * 
	 * @return
	 ******************************************************************************/

	public void depositMoneyOnHolderAccount(Account account) {

		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		account.setAccountHolderBalance(account.getAccountHolderBalance() + Math.abs(amount));
		addTAccountHistoryMovement(account, amount.toString());
		return;
	}

	public void depositMoneyOnSecondaryAccount(Account account) {
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
	/******************************************************************************
	 * withdraw money on accounts
	 * 
	 * @return
	 ******************************************************************************/
	public void withdrawMoneyOnHolderAccount(Account account) {

		System.out.println("Please indicate the amount of money you wish to take");
		Double amount = scanner.nextDouble();

		if ((account.getAccountHolderBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}
		account.setAccountHolderBalance(account.getAccountHolderBalance() - Math.abs(amount));
		addTAccountHistoryMovement(account, "-" + amount.toString());
		System.out.printf("\nAt the moment you have %f on your account\n", account.getAccountHolderBalance());
		return;
	}

	public void withdrawMoneyOnSecondaryAccount(Account account) {
		String secondaryAccount = checkSecondaryAccount(account);
		String decision;

		if (secondaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECONDARY NUMBER CORRECTO OU QUERER SAIR

		for (Account getAccount : accounts) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccount)) {
				withdrawMoneyOnHolderAccount(getAccount);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;
	}

	public String checkSecondaryAccount(Account account) {
		System.out.println("From which of your secondary account do want to perform action? ");
		System.out.printf("\nThe accounts that you have associated are: %s",
				Arrays.toString(account.getSecondaryAccountNumber()));
		String secondaryAccount;

		secondaryAccount = scanner.next();
		for (String otherAccounts : account.getSecondaryAccountNumber()) {
			if (otherAccounts.equals(secondaryAccount)) {
				System.out.println("The choosen account is: " + otherAccounts);
				return otherAccounts;
			}
		}
		System.err.println("The secondary account does not correspond to the ones that you own");
		return null;
	}
	/******************************************************************************
	 * Transfer money on any account
	 * 
	 * @return
	 ******************************************************************************/

	public void transferMoney(Account account) {

		Double amount;
		String accountToTransfer;

		System.out.println("Please indicate the amount of money you wish to transfer");
		amount = scanner.nextDouble();

		if (account.getAccountHolderBalance() - Math.abs(amount) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}

		System.out.println("Please indicate to which account do you want to transfer?");
		accountToTransfer = scanner.next();
		for (Account customerToTransfer : accounts) {
			if (customerToTransfer == null)
				break;
			if (customerToTransfer.getAccountHolderNumber().equals(accountToTransfer)) {
//				System.out.printf("\nThe account belongs to Name: %s, TaxId: %s and Account Number: %s\n",
//						customerToTransfer.getName(), customerToTransfer.getTaxId(),
//						customerToTransfer.getAccountHolderNumber());
				customerToTransfer.setAccountHolderBalance(customerToTransfer.getAccountHolderBalance() + amount);
				account.setAccountHolderBalance(account.getAccountHolderBalance() - Math.abs(amount));
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

	/******************************************************************************
	 * check if the account holder and password are correct
	 * 
	 * @return
	 ******************************************************************************/
	public Account checkAccountNameAndPassword() {
		System.out.println("Please write your account holder number");
		String accountHolderNumber = scanner.next();
		System.out.println("Please write your Password account");
		String password = scanner.next();

		for (Account account : accounts) {
			if (account == null)
				break;
			if (account.getAccountHolderNumber().equals(accountHolderNumber)
					&& account.getPasswordAccount().equals(password)) {
				return account;
			}
		}
		System.err.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}

	/******************************************************************************
	 * Prints all accounts
	 * 
	 * 
	 ******************************************************************************/
	public void showAccountsDetails() {
		for (Account account : accounts) {
			System.out.println("The account: ");
			System.out.println(account.toString());
		}
	}

	/******************************************************************************
	 * Account History movement
	 *
	 ******************************************************************************/

	public void showAccountHistoryMovement(Account account) {
		for (String transactionHistory : account.getTransactionHistory()) {
			System.out.println("The transaction history: " + transactionHistory);
		}
		System.out.println("The total amount of: " + account.getAccountHolderBalance());
	}

	/******************************************************************************
	 * Add to account history
	 * 
	 * 
	 ******************************************************************************/

	public void addTAccountHistoryMovement(Account account, String amount) {
		account.getTransactionHistory().add(amount);
	}
	
	/******************************************************************************
	 * Find Customer Account
	 * 
	 * 
	 ******************************************************************************/
	public Account findCustomerAccount(int customerId) {
		for (Account account : accounts) {
			if(account.getCustomerId().equals(customerId)) {
				System.out.println("Account found");
				return account;
			}
		}
		System.out.println("Customer does not have an account");
		return null;
	}
	
	/******************************************************************************
	 * Display account by customer Id
	 * 
	 * 
	 ******************************************************************************/
	public void displayAccount(int customerId) {
		Account account = findCustomerAccount(customerId);
		System.out.println(account.toString());
	}
	
	
	
	/******************************************************************************
	 * Display methods
	 * 
	 * 
	 ******************************************************************************/
	

	public void displaySecondaryAccountsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Secondary Account");
		System.out.println("2 - Delete Secondary Account");
		System.out.println("###########################################################################");
	}

	public void displayMoneyManagementMenu() {
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
