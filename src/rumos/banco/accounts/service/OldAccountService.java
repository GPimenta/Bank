package rumos.banco.accounts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import rumos.banco.accounts.model.old.OldAccount;
import rumos.banco.accounts.repository.OldIAccountRepository;
import rumos.banco.customers.model.old.OldCustomer;

public class OldAccountService {

	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";
	private static final String NO_MONEY_TO_REMOVE = "You are removing an amount bigger than what you own";

//	private static ArrayList<Account> accounts = new ArrayList<>();
//	private static Scanner scanner = new Scanner(System.in);
//	private static Integer id = 0;

	private OldIAccountRepository repository;

	/******************************************************************************
	 * Constructor
	 * 
	 * 
	 ******************************************************************************/
	public OldAccountService(OldIAccountRepository repository) {
		this.repository = repository;
	}

	/******************************************************************************
	 * Add Account
	 * 
	 * 
	 ******************************************************************************/

	public void create(OldAccount account) {
		if (account == null) {
			throw new IllegalArgumentException("Failed to create account - Invalid account object");
		}
		repository.create(account);
	}

	/******************************************************************************
	 * Delete Account
	 * 
	 * @return
	 ******************************************************************************/
	public void deleteAccount(Integer customerId) {
		for (OldAccount account : repository.getAll()) {
			if (account.getCustomerId().equals(customerId)) {
				System.out.println("Removing account");
				repository.deleteByCustomerId(customerId);
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
	public void addSecondaryAccount(OldAccount account, String secondAccount) {
		Integer index = checkIfPossibleToAddSecondaryAccounts(account);

		if (index < 4) {
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
		for (OldAccount account : repository.getAll()) {
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

	public int checkIfPossibleToAddSecondaryAccounts(OldAccount account) {
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

	public void deleteSecondaryAccount(OldAccount account, String secondaryAccount) {

		if (!checkIfItHasSecondaryAccounts(account))
			return;

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

	public boolean checkIfItHasSecondaryAccounts(OldAccount account) {

		for (String secondaryAccounts : account.getSecondaryAccountNumber()) {
			if (secondaryAccounts.isBlank())
				return true;
		}
		System.err.println("You do not have Secondary accounts to delete.");
		return false;
	}

	public int countSecondaryAccounts(OldAccount account) {
		int count = 0;
		for (int i = 0; i < account.getSecondaryAccountNumber().length; i++) {
			if (account.getSecondaryAccountNumber()[i].isBlank()) {
				count++;
			}
		}
		return count;
	}

	public int countAllHolderAccounts() {
		return repository.getAll().size();
	}

	public int howManySecondaryAccountArePossibleToAdd(OldAccount account) {

		int totalSecondaryAccounts = countAllHolderAccounts() - countSecondaryAccounts(account);

		if (totalSecondaryAccounts >= 0) {
			System.out.println("Its possible do add secondary accounts");
			return countSecondaryAccounts(account);
		}
		if (countAllHolderAccounts() == 0) {
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

	public void depositMoneyOnHolderAccount(Integer customerId, Double amount) {
		OldAccount account = findCustomerAccount(customerId);

		account.setAccountHolderBalance(account.getAccountHolderBalance() + Math.abs(amount));
		addTAccountHistoryMovement(account, amount.toString());
		return;
	}

	public void depositMoneyOnSecondaryAccount(Integer customerId, Double amount, String secondaryAccount) {
		OldAccount account = findCustomerAccount(customerId);

		String secondaryAccountDeposit = checkSecondaryAccount(account, secondaryAccount);

		if (secondaryAccountDeposit == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECONDARY NUMBER CORRECTO OU QUERER SAIR

		for (OldAccount getAccount : repository.getAll()) {
			// TODO isto nao deve funcionar sendo q estou a buscar uma copia do objeto e nao
			// o objeto para mudar
			if (getAccount.getAccountHolderNumber().equals(secondaryAccountDeposit)) {
				depositMoneyOnHolderAccount(getAccount.getCustomerId(), amount);
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
	public void withdrawMoneyOnHolderAccount(Integer customerId, Double amount, String decision) {
		OldAccount account = findCustomerAccount(customerId);

		if ((account.getAccountHolderBalance() - Math.abs(amount)) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);
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

	public void withdrawMoneyOnSecondaryAccount(Integer customerId, Double amount, String decision,
			String secondaryAccount) {
		OldAccount account = findCustomerAccount(customerId);

		String secondaryAccountToWithdraw = checkSecondaryAccount(account, secondaryAccount);

		if (secondaryAccountToWithdraw == null)
			return; // NECESSARIO FAZER LOOP PARA POR O SECONDARY NUMBER CORRECTO OU QUERER SAIR

		for (OldAccount getAccount : repository.getAll()) {
			if (getAccount.getAccountHolderNumber().equals(secondaryAccountToWithdraw)) {
				withdrawMoneyOnHolderAccount(getAccount.getCustomerId(), amount, decision);
				return;
			}
		}
		System.out.println("There is no Holder account");
		return;
	}

	public String checkSecondaryAccount(OldAccount account, String secondaryAccount) {

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

	public void transferMoney(Integer customerId, Double amount, String accountToTransfer) {

		OldAccount account;

		account = findCustomerAccount(customerId);
		if (account == null)
			return;

		if (account.getAccountHolderBalance() - Math.abs(amount) < 0D) {
			System.out.println(NO_MONEY_TO_REMOVE);/* NECESSARIO INCLUIR PLAFOND DO CARTAO DE CREDITO */
			return;
		}

		for (OldAccount customerToTransfer : repository.getAll()) {
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
	public OldAccount checkAccountNameAndPassword(String accountHolderNumber, String password) {

		for (OldAccount account : repository.getAll()) {
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
		for (OldAccount account : repository.getAll()) {
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
		OldAccount account = findCustomerAccount(customerId);
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

	public void addTAccountHistoryMovement(OldAccount account, String amount) {
		account.getTransactionHistory().add(amount);
	}

	/******************************************************************************
	 * Find Customer Account
	 * 
	 * 
	 ******************************************************************************/
	public OldAccount findCustomerAccount(Integer customerId) {
		for (OldAccount account : repository.getAll()) {
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
	public void editAccountPassword(Integer customerId, String password) {
		OldAccount account = findCustomerAccount(customerId);

		account.setPasswordAccount(password);
		return;

	}

	/******************************************************************************
	 * Display account by customer Id
	 * 
	 * 
	 ******************************************************************************/
	public void displayAccount(Integer customerId) {
		OldAccount account = findCustomerAccount(customerId);
		System.out.println(account.toString());
	}

	/******************************************************************************
	 * Withdraw money from Cash Advance
	 * 
	 * 
	 ******************************************************************************/
	public void withdrawMoneyFromCashAdvance(Integer customerId, Double amount) {
		OldAccount account = findCustomerAccount(customerId);

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
	public void checkCashAdvance(OldAccount account, Double amount) {
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