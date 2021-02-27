package rumos.banco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import rumos.banco.model.Account;
import rumos.banco.model.Customer;

public class AccountService {

	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
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
	 * Delete Account
	 * 
	 * @return
	 ******************************************************************************/
	public void deleteAccount(Integer customerId) {
		for (Account account : accounts) {
			if (account.getCustomerId().equals(customerId)) {
				System.out.println("Removing account");
				accounts.remove(account);
				return;
			}
		}
		System.err.println("Account not found");
		return;
	}

	/******************************************************************************
	 * Secondary Accounts
	 * 
	 * @return
	 ******************************************************************************/
	public void addSecondaryAccount(Account account) {
		Integer index = checkSecondaryAccounts(account);
		String secondAccount;

		if (index < 4) {
			System.out.println("Please indicate what account do you wish to have as Second account?");
			secondAccount = scanner.next();
			for (String checkIfTheSameAccount : account.getSecondaryAccountNumber()) {
				if (checkIfTheSameAccount.equals(secondAccount)) {
					System.err.println(
							"The account that you are requesting to be secondary it is already your secondary account");
					return;
				}
			}
			if (account.getAccountHolderNumber().equals(secondAccount)) {
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
				System.out.println("You are elegible to add secondary accounts");
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

	public int countSecondaryAccounts(Account account) {
		int count = 0;
		for (int i = 0; i < account.getSecondaryAccountNumber().length; i++) {
			if (account.getSecondaryAccountNumber()[i].isBlank()) {
				count++;
			}
		}
		return count;
	}

	public int countAllHolderAccounts() {
		return accounts.size();
	}

	public int howManySecondaryAccountArePossibleToAdd(Account account) {

		int totalSecondaryAccounts = countAllHolderAccounts() - countSecondaryAccounts(account);

		if (totalSecondaryAccounts >= 0) {
			System.out.println("Its possible do add secondary accounts");
			return countSecondaryAccounts(account);
		}
		if(countAllHolderAccounts() == 0) {
			System.out.println("Your are the first account, impossible to add secondary Accounts");
			return countAllHolderAccounts();
		}
		System.out.printf("\nIts possible to add %d secondary accounts\n", countAllHolderAccounts());
		return countAllHolderAccounts();

	}

	/******************************************************************************
	 * Deposit money on accounts
	 * 
	 * @return
	 ******************************************************************************/

	public void depositMoneyOnHolderAccount(Integer customerId) {
		Account account = findCustomerAccount(customerId);

		System.out.println("Please insert the amount to deposit in your account");
		Double amount = scanner.nextDouble();
		account.setAccountHolderBalance(account.getAccountHolderBalance() + Math.abs(amount));
		addTAccountHistoryMovement(account, amount.toString());
		return;
	}

	public void depositMoneyOnSecondaryAccount(Integer customerId) {
		Account account = findCustomerAccount(customerId);

		String secondaryAccount = checkSecondaryAccount(account);
		String decision;

		if (secondaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECUNDARY NUMBER CORRECTO OU QUERER SAIR

		for (Account getAccount : accounts) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccount)) {
				depositMoneyOnHolderAccount(getAccount.getCustomerId());
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
	public void withdrawMoneyOnHolderAccount(Integer customerId) {
		Account account = findCustomerAccount(customerId);

		System.out.println("Please indicate the amount of money you wish to take");
		Double amount = scanner.nextDouble();
		String decision;

		if ((account.getAccountHolderBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);
			System.out.println("Do you wish to use the cash advance? y/n");
			decision = scanner.next();
			if (decision.equals("y")) {
				withdrawMoneyFromCashAdvance(customerId, Math.abs(amount) - account.getAccountHolderBalance());
				account.setAccountHolderBalance(0.0);
				addTAccountHistoryMovement(account, "-" + amount.toString());
				return;
			}
		}
		account.setAccountHolderBalance(account.getAccountHolderBalance() - Math.abs(amount));
		addTAccountHistoryMovement(account, "-" + amount.toString());
		System.out.printf("\nAt the moment you have %f on your account\n", account.getAccountHolderBalance());
		return;
	}

	public void withdrawMoneyOnSecondaryAccount(Integer customerId) {
		Account account = findCustomerAccount(customerId);

		String secondaryAccount = checkSecondaryAccount(account);
		String decision;

		if (secondaryAccount == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECONDARY NUMBER CORRECTO OU QUERER SAIR

		for (Account getAccount : accounts) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccount)) {
				withdrawMoneyOnHolderAccount(getAccount.getCustomerId());
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

	public void transferMoney(Integer customerId) {

		Double amount;
		String accountToTransfer;
		Account account;

		account = findCustomerAccount(customerId);
		if (account == null)
			return;

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
				continue;
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
	 * Print account
	 * 
	 * 
	 ******************************************************************************/
	public void showAccountDetails(Integer customerId) {
		if (customerId == null)
			return;
		System.out.println(findCustomerAccount(customerId).toString());
		return;
	}

	/******************************************************************************
	 * Account History movement
	 *
	 ******************************************************************************/

	public void showAccountHistoryMovement(Integer customerId) {
		Account account = findCustomerAccount(customerId);
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
	public Account findCustomerAccount(Integer customerId) {
		for (Account account : accounts) {
			if (account.getCustomerId().equals(customerId)) {
				System.out.println("Account found");
				return account;
			}
		}
		System.err.println("Customer does not have an account");
		return null;
	}

	/******************************************************************************
	 * Edit Account details
	 * 
	 * 
	 ******************************************************************************/
	public void editAccountPassword(Integer customerId) {
		Account account = findCustomerAccount(customerId);

		System.out.println("Please write the new password for account");
		account.setPasswordAccount(scanner.next());
		return;

	}

	/******************************************************************************
	 * Display account by customer Id
	 * 
	 * 
	 ******************************************************************************/
	public void displayAccount(Integer customerId) {
		Account account = findCustomerAccount(customerId);
		System.out.println(account.toString());
	}

	/******************************************************************************
	 * Withdraw money from Cash Advance
	 * 
	 * 
	 ******************************************************************************/
	public void withdrawMoneyFromCashAdvance(Integer customerId, Double amount) {
		Account account = findCustomerAccount(customerId);

		if (account.getCheckEligability()) {
			checkCashAdvance(account, amount);
			return;
		}
		System.err.println("You have spent all the money of the cash-advance");
		return;
	}

	/******************************************************************************
	 * Check Cash Advance
	 * 
	 * 
	 ******************************************************************************/
	public void checkCashAdvance(Account account, Double amount) {
		if (account.getCashAdvanceQuantity() - amount > 0) {
			account.setCashAdvanceQuantity(account.getCashAdvanceQuantity() - amount);
			System.out.println(
					"The amount of money on the cash-advance is: " + account.getCashAdvanceQuantity().toString());
			return;
		}
		account.setCheckEligability(false);
		System.err.printf("\nYou cannot spend %f, you only have %f\n", amount, account.getAccountHolderBalance());
		return;

	}
}